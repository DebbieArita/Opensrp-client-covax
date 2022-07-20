package com.example.opensrp_client_covacs.application;

import android.content.Intent;
import android.os.Handler;

import com.evernote.android.job.JobManager;
import com.example.opensrp_client_covacs.BuildConfig;
import com.example.opensrp_client_covacs.activity.ChildFormActivity;
import com.example.opensrp_client_covacs.activity.ChildImmunizationActivity;
import com.example.opensrp_client_covacs.activity.ChildProfileActivity;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.activity.LoginActivity;
import com.example.opensrp_client_covacs.domain.ChildMetadata;
import com.example.opensrp_client_covacs.job.AppJobCreator;
import com.example.opensrp_client_covacs.provider.ChildRegisterQueryProvider;
import com.example.opensrp_client_covacs.repository.CovacsRepository;
import com.example.opensrp_client_covacs.util.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import org.smartregister.commonregistry.CommonFtsObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.helper.JsonSpecHelper;
import org.smartregister.immunization.ImmunizationLibrary;
import org.smartregister.immunization.domain.VaccineSchedule;
import org.smartregister.immunization.domain.jsonmapping.Vaccine;
import org.smartregister.immunization.domain.jsonmapping.VaccineGroup;
import org.smartregister.immunization.repository.RecurringServiceRecordRepository;
import org.smartregister.immunization.repository.RecurringServiceTypeRepository;
import org.smartregister.immunization.repository.VaccineRepository;
import org.smartregister.immunization.util.VaccinateActionUtils;
import org.smartregister.immunization.util.VaccinatorUtils;
import org.smartregister.location.helper.LocationHelper;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;
import org.smartregister.repository.EventClientRepository;
import org.smartregister.repository.Repository;
import org.smartregister.repository.UniqueIdRepository;
import org.smartregister.sync.ClientProcessorForJava;
import org.smartregister.sync.DrishtiSyncScheduler;
import org.smartregister.sync.helper.ECSyncHelper;
import org.smartregister.util.AppExecutors;
import org.smartregister.util.AppProperties;
import org.smartregister.view.LocationPickerView;
import org.smartregister.view.activity.DrishtiApplication;
import org.smartregister.view.receiver.TimeChangedBroadcastReceiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class CovacsApplication extends DrishtiApplication implements TimeChangedBroadcastReceiver.OnTimeChangedListener{

    private static List<VaccineGroup> vaccineGroups;
    private static CommonFtsObject commonFtsObject;
    private static JsonSpecHelper jsonSpecHelper;
    private EventClientRepository eventClientRepository;
    private ECSyncHelper ecSyncHelper;
    private AppExecutors appExecutors;
    private UniqueIdRepository uniqueIdRepository;
    private LocationPickerView locationPickerView;
    private EventBus eventBus;
    private ClientProcessorForJava clientProcessorForJava;



    //init json helper
    public static JsonSpecHelper getJsonSpecHelper() {
        return jsonSpecHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        context = Context.getInstance();
        context.updateApplicationContext(getApplicationContext());
        context.updateCommonFtsObject(createCommonFtsObject(context.applicationContext()));

        //Initialize Modules
        CoreLibrary.init(context, new AppSyncConfiguration(), BuildConfig.BUILD_TIMESTAMP);
        ImmunizationLibrary.init(context, getRepository(), createCommonFtsObject(context.applicationContext()),
                BuildConfig.VERSION_CODE, BuildConfig.DATABASE_VERSION);
        ImmunizationLibrary.getInstance().setVaccineSyncTime(3, TimeUnit.MINUTES);
        ConfigurableViewsLibrary.init(context);
        LocationHelper.init(new ArrayList<>(Arrays.asList(BuildConfig.ALLOWED_LEVELS)), BuildConfig.DEFAULT_LOCATION);

        CovacsApplication.getInstance().setEventBus(EventBus.getDefault());

//        EventBus.getDefault().register(this);

//        setDefaultLanguage();

        initRepositories();
        initOfflineSchedules();
//        setOpenSRPUrl();


        LocationHelper.init(new ArrayList<>(Arrays.asList(BuildConfig.ALLOWED_LEVELS)), BuildConfig.DEFAULT_LOCATION);

        jsonSpecHelper = new JsonSpecHelper(this);

        //init Job Manager
        JobManager.create(this).addJobCreator(new AppJobCreator());
        SyncStatusBroadcastReceiver.init(this);
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

//    private void setDefaultLanguage() {
//        try {
//            Utils.saveLanguage("en");
//        } catch (Exception e) {
//            Timber.e(e, " --> saveLanguage");
//        }
//    }

    private static void populateAlertColumnNames(List<Vaccine> vaccines, List<String> names) {

        for (Vaccine vaccine : vaccines)
            if (vaccine.getVaccineSeparator() != null && vaccine.getName().contains(vaccine.getVaccineSeparator().trim())) {
                String[] individualVaccines = vaccine.getName().split(vaccine.getVaccineSeparator().trim());

                List<Vaccine> vaccineList = new ArrayList<>();
                for (String individualVaccine : individualVaccines) {
                    Vaccine vaccineClone = new Vaccine();
                    vaccineClone.setName(individualVaccine.trim());
                    vaccineList.add(vaccineClone);

                }
                populateAlertColumnNames(vaccineList, names);


            } else {

                names.add("alerts." + VaccinateActionUtils.addHyphen(vaccine.getName()));
            }
    }


    public static CommonFtsObject createCommonFtsObject(android.content.Context context) {
        if (commonFtsObject == null) {
            commonFtsObject = new CommonFtsObject(getFtsTables());
            for (String ftsTable : commonFtsObject.getTables()) {
                commonFtsObject.updateSearchFields(ftsTable, getFtsSearchFields(ftsTable));
                commonFtsObject.updateSortFields(ftsTable, getFtsSortFields(ftsTable, context));
//                commonFtsObject.updateMainConditions(ftsTable, getFtsMainConditions(ftsTable));
            }
        }
//        commonFtsObject.updateAlertScheduleMap(getAlertScheduleMap(context));

        return commonFtsObject;
    }


    private static String[] getFtsTables() {
        return new String[]{AppConstants.RegisterTable.CLIENT};
    }

    private static String[] getFtsSearchFields(String tableName) {
        if (AppConstants.RegisterTable.CHILD_DETAILS.equals(tableName)) {
            return new String[]{
                    AppConstants.KeyConstants.ZEIR_ID,
                    AppConstants.KeyConstants.FIRST_NAME,
                    AppConstants.KeyConstants.LAST_NAME
            };
//            case AppConstants.RegisterTable.CHILD_DETAILS:
//                return new String[]{AppConstants.KeyConstants.LOST_TO_FOLLOW_UP, AppConstants.KeyConstants.INACTIVE};
        }
        return null;
    }

    private static String[] getFtsSortFields(String tableName, android.content.Context context) {
        if (AppConstants.RegisterTable.CHILD_DETAILS.equals(tableName)) {
            List<VaccineGroup> vaccineList = VaccinatorUtils.getVaccineGroupsFromVaccineConfigFile(context, VaccinatorUtils.vaccines_file);
            List<String> names = new ArrayList<>();
            names.add(AppConstants.KeyConstants.ZEIR_ID);
            names.add(AppConstants.KeyConstants.REGISTRATION_DATE);
            names.add(AppConstants.KeyConstants.USERNAME);
            names.add(AppConstants.KeyConstants.REACTION_VACCINE);


            for (VaccineGroup vaccineGroup : vaccineList) {
                populateAlertColumnNames(vaccineGroup.vaccines, names);
            }

            return names.toArray(new String[0]);
        }
        return null;
    }

//    private static String[] getFtsMainConditions(String tableName){
//        if(AppConstants.RegisterTable.CHILD_DETAILS.equals(tableName)) {
//            String[] mainConditions = {"Field1"};
//            return mainConditions;
//        }
//        return null;
//    }


    public static synchronized CovacsApplication getInstance() {
        return (CovacsApplication) mInstance;
    }

    public AppProperties getProperties() {
        return CoreLibrary.getInstance().context().getAppProperties();
    }

    public LocationPickerView getLocationPickerView(android.content.Context context) {
        if (locationPickerView == null) {
            locationPickerView = new LocationPickerView(context);
            new Handler(context.getMainLooper()).post(() -> locationPickerView.init());

        }
        return locationPickerView;
    }

//    public ChildMetadata getMetadata() {
//        ChildMetadata metadata = FormUtils.getMetadata(new ChildProfileActivity(), getDefaultLocationLevel(), getFacilityHierarchy());
//        HashMap<String, String> setting = new HashMap<>();
//        return metadata;
//    }

    public ChildMetadata getMetadata() {
        ChildMetadata metadata = new ChildMetadata(ChildFormActivity.class, ChildProfileActivity.class,
                ChildImmunizationActivity.class, ChildRegisterActivity.class, true, new ChildRegisterQueryProvider());
        metadata.updateChildRegister(AppConstants.JsonForm.CHILD_ENROLLMENT, AppConstants.RegisterTable.CHILD_DETAILS,
                AppConstants.EventType.CHILD_REGISTRATION, AppConstants.EventType.UPDATE_CHILD_REGISTRATION, AppConstants.ConfigurationConstants.CHILD_REGISTER);
        metadata.setFieldsWithLocationHierarchy(new HashSet<>(Arrays.asList("county", "health_facility")));
        metadata.setLocationLevels(new ArrayList<>(Arrays.asList(BuildConfig.LOCATION_LEVELS)));
        metadata.setHealthFacilityLevels(new ArrayList<>(Arrays.asList(BuildConfig.HEALTH_FACILITY_LEVELS)));
        return metadata;
    }

    public UniqueIdRepository getUniqueIdRepository() {
        if (uniqueIdRepository == null) {
            uniqueIdRepository = new UniqueIdRepository();
        }
        return uniqueIdRepository;
    }

    public EventBus getEventBus() {

        if (eventBus == null) {
            Timber.e(" Event Bus instance does not exist!!! Pass the Implementing Application's Eventbus by invoking the " +
                    CovacsApplication.class.getCanonicalName() + ".setEventBus method from the onCreate method of " + "your Application class ");
        }
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }



//    private ArrayList<String> getFacilityHierarchy() {
//        return null;
//    }
//
//    private String getDefaultLocationLevel() {
//        return null;
//    }




//    public static List<VaccineGroup> getVaccineGroups(android.content.Context context) {
//        if (vaccineGroups == null) {
//            vaccineGroups = VaccinatorUtils.getVaccineGroupsFromVaccineConfigFile(context, VaccinatorUtils.vaccines_file);
//        }
//        return vaccineGroups;
//    }


    private void initRepositories() {
        vaccineRepository();

    }

    private void initOfflineSchedules() {
        try {
            List<VaccineGroup> vaccines = VaccinatorUtils.getSupportedVaccines(this);
            List<Vaccine> specialVaccines = VaccinatorUtils.getSpecialVaccines(this);
            VaccineSchedule.init(vaccines, specialVaccines, AppConstants.KeyConstants.CHILD);
        } catch (Exception e) {
            Timber.e(e, "CovacsApplication --> initOfflineSchedules");
        }
    }


    public VaccineRepository vaccineRepository() {
        return ImmunizationLibrary.getInstance().vaccineRepository();
    }


//    public AllCommonsRepository getAllCommonsRepository(String table) {
//        return CovacsApplication.getInstance().getContext().allCommonsRepositoryobjects(table);
//    }




    @Override
    public void logoutCurrentUser() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getApplicationContext().startActivity(intent);
        context.userService().logoutSession();
        Timber.i("Logged out user %s", getContext().allSharedPreferences().fetchRegisteredANM());
    }

    @Override
    public Repository getRepository() {
        try {
            if (repository == null) {
                repository = new CovacsRepository(getApplicationContext(), context);
            }
        } catch (UnsatisfiedLinkError e) {
            Timber.e(e, "CovacsApplication --> getRepository");
        }
        return repository;
    }

    public Context getContext() {
        return context;
    }

    public ClientProcessorForJava getClientProcessorForJava() {
        if (clientProcessorForJava == null) {
            clientProcessorForJava = DrishtiApplication.getInstance().getClientProcessor();
        }
        return clientProcessorForJava;
    }


    @Override
    public void onTerminate() {
        Timber.i("Application is terminating. Stopping sync scheduler and resetting isSyncInProgress setting.");
        cleanUpSyncState();
        TimeChangedBroadcastReceiver.destroy(this);
        SyncStatusBroadcastReceiver.destroy(this);
        super.onTerminate();
    }

    protected void cleanUpSyncState() {
        try {
            DrishtiSyncScheduler.stop(getApplicationContext());
            context.allSharedPreferences().saveIsSyncInProgress(false);
        } catch (Exception e) {
            Timber.e(e, "CovacsApplication --> cleanUpSyncState");
        }
    }

    @Override
    public void onTimeChanged() {
        context.userService().forceRemoteLogin(context().allSharedPreferences().fetchRegisteredANM());
        logoutCurrentUser();

    }

    @Override
    public void onTimeZoneChanged() {
        context.userService().forceRemoteLogin(context().allSharedPreferences().fetchRegisteredANM());
        logoutCurrentUser();
    }

    public Context context() {
        return context;
    }

    public EventClientRepository eventClientRepository() {
        if (eventClientRepository == null) {
            eventClientRepository = new EventClientRepository();
        }
        return eventClientRepository;
    }

    public ECSyncHelper getEcSyncHelper() {
        if (ecSyncHelper == null) {
            ecSyncHelper = ECSyncHelper.getInstance(getApplicationContext());
        }
        return ecSyncHelper;
    }



    public RecurringServiceTypeRepository getRecurringServiceTypeRepository() {
        return ImmunizationLibrary.getInstance().recurringServiceTypeRepository();
    }

    public RecurringServiceRecordRepository getRecurringServiceRecordRepository() {
        return ImmunizationLibrary.getInstance().recurringServiceRecordRepository();
    }


    public String getSyncLocations() {
        if (LocationHelper.getInstance() != null && LocationHelper.getInstance().locationIdsFromHierarchy() != null)
            return LocationHelper.getInstance().locationIdsFromHierarchy();
        return "";
    }
    public AppExecutors getAppExecutors() {
        if (appExecutors == null) {
            appExecutors = new AppExecutors();
        }
        return appExecutors;
    }

    public boolean allowLazyProcessing() {
        return true;
    }
}
