package com.example.opensrp_client_covacs.application;

import android.content.Intent;
import android.util.Pair;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatDelegate;

import com.evernote.android.job.JobManager;
import com.example.opensrp_client_covacs.BuildConfig;
import com.example.opensrp_client_covacs.activity.ChildFormActivity;
import com.example.opensrp_client_covacs.activity.ChildImmunizationActivity;
import com.example.opensrp_client_covacs.activity.ChildProfileActivity;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.activity.LoginActivity;
import com.example.opensrp_client_covacs.job.AppJobCreator;
import com.example.opensrp_client_covacs.repository.AppChildRegisterQueryProvider;
import com.example.opensrp_client_covacs.repository.CovacsRepository;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.util.AppUtils;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.jetbrains.annotations.NotNull;
import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import org.smartregister.commonregistry.CommonFtsObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.configurableviews.helper.JsonSpecHelper;
import org.smartregister.immunization.ImmunizationLibrary;
import org.smartregister.immunization.domain.jsonmapping.Vaccine;
import org.smartregister.immunization.domain.jsonmapping.VaccineGroup;
import org.smartregister.immunization.repository.RecurringServiceRecordRepository;
import org.smartregister.immunization.repository.RecurringServiceTypeRepository;
import org.smartregister.immunization.repository.VaccineRepository;
import org.smartregister.immunization.util.VaccinatorUtils;
import org.smartregister.location.helper.LocationHelper;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;
import org.smartregister.repository.EventClientRepository;
import org.smartregister.repository.Repository;
import org.smartregister.sync.ClientProcessorForJava;
import org.smartregister.sync.DrishtiSyncScheduler;
import org.smartregister.sync.helper.ECSyncHelper;
import org.smartregister.util.AppExecutors;
import org.smartregister.view.activity.DrishtiApplication;
import org.smartregister.view.receiver.TimeChangedBroadcastReceiver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class CovacsApplication extends DrishtiApplication implements TimeChangedBroadcastReceiver.OnTimeChangedListener{

    private static List<VaccineGroup> vaccineGroups;
    private static CommonFtsObject commonFtsObject;
    private static JsonSpecHelper jsonSpecHelper;
    private boolean lastModified;
    private ClientProcessorForJava clientProcessorForJava;
    private EventClientRepository eventClientRepository;
    private ECSyncHelper ecSyncHelper;
    private AppExecutors appExecutors;

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

//        ImmunizationLibrary.init(context, getRepository(), createCommonFtsObject(context.applicationContext()),
//                BuildConfig.VERSION_CODE, BuildConfig.DATABASE_VERSION);
//        ImmunizationLibrary.getInstance().setVaccineSyncTime(3, TimeUnit.MINUTES);

        ConfigurableViewsLibrary.init(context);

//        setDefaultLanguage();

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(!BuildConfig.DEBUG);

        initRepositories();
//        initOfflineSchedules();
//        setOpenSRPUrl();

        SyncStatusBroadcastReceiver.init(this);
        LocationHelper.init(new ArrayList<>(Arrays.asList(BuildConfig.ALLOWED_LEVELS)), BuildConfig.DEFAULT_LOCATION);

        jsonSpecHelper = new JsonSpecHelper(this);

        //init Job Manager
        JobManager.create(this).addJobCreator(new AppJobCreator());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

//    private void setDefaultLanguage() {
//        try {
//            Utils.saveLanguage("en");
//        } catch (Exception e) {
//            Timber.e(e, " --> saveLanguage");
//        }
//    }


    public static CommonFtsObject createCommonFtsObject(android.content.Context context) {
        if (commonFtsObject == null) {
            commonFtsObject = new CommonFtsObject(getFtsTables());
            for (String ftsTable : commonFtsObject.getTables()) {
                commonFtsObject.updateSearchFields(ftsTable, getFtsSearchFields(ftsTable));
                commonFtsObject.updateSortFields(ftsTable, getFtsSortFields(ftsTable, context));
            }
        }
//        commonFtsObject.updateAlertScheduleMap(getAlertScheduleMap(context));

        return commonFtsObject;
    }


    private static String[] getFtsTables() {
        return new String[]{AppConstants.RegisterTable.CLIENT};
    }

    private static String[] getFtsSearchFields(String tableName) {
        if (AppConstants.RegisterTable.CLIENT.equals(tableName)) {
            return new String[]{
                    AppConstants.KeyConstants.ZEIR_ID,
                    AppConstants.KeyConstants.FIRST_NAME,
                    AppConstants.KeyConstants.LAST_NAME
            };
//            case DBConstants.RegisterTable.CHILD_DETAILS:
//                return new String[]{DBConstants.KEY.LOST_TO_FOLLOW_UP, DBConstants.KEY.INACTIVE};
        }
        return null;
    }

    private static String[] getFtsSortFields(String tableName, android.content.Context context) {
        switch (tableName) {
            case AppConstants.TableNameConstants.ALL_CLIENTS:
                return Arrays.asList(AppConstants.KeyConstants.FIRST_NAME, AppConstants.KeyConstants.LAST_NAME,
                        AppConstants.KeyConstants.DOB, AppConstants.KeyConstants.ZEIR_ID, AppConstants.KeyConstants.GENDER,
                        AppConstants.KeyConstants.COUNTY, AppConstants.KeyConstants.HEALTH_FACILITY, AppConstants.KeyConstants.REGISTRATION_DATE).toArray(new String[0]);
//                        AppConstants.KeyConstants.DOD, AppConstants.KeyConstants.DATE_REMOVED).toArray(new String[0]);
            case AppConstants.RegisterTable.CLIENT:
                List<VaccineGroup> vaccineList = VaccinatorUtils.getVaccineGroupsFromVaccineConfigFile(context, VaccinatorUtils.vaccines_file);
                List<String> names = new ArrayList<>();
                names.add(AppConstants.KeyConstants.ZEIR_ID);
                names.add(AppConstants.KeyConstants.REGISTRATION_DATE);
                names.add(AppConstants.KeyConstants.USERNAME);
                names.add(AppConstants.KeyConstants.REACTION_VACCINE);


//                for (VaccineGroup vaccineGroup : vaccineList) {
//                    populateAlertColumnNames(vaccineGroup.vaccines, names);
//                }

                return names.toArray(new String[0]);

            default:
                return null;
        }
    }

//    private static void populateAlertColumnNames(List<Vaccine> vaccines, List<String> names) {
//
//        for (Vaccine vaccine : vaccines)
//            if (vaccine.getVaccineSeparator() != null && vaccine.getName().contains(vaccine.getVaccineSeparator().trim())) {
//                String[] individualVaccines = vaccine.getName().split(vaccine.getVaccineSeparator().trim());
//
//                List<Vaccine> vaccineList = new ArrayList<>();
//                for (String individualVaccine : individualVaccines) {
//                    Vaccine vaccineClone = new Vaccine();
//                    vaccineClone.setName(individualVaccine.trim());
//                    vaccineList.add(vaccineClone);
//
//                }
//                populateAlertColumnNames(vaccineList, names);
//            } else {
//                names.add("alerts." + VaccinateActionUtils.addHyphen(vaccine.getName()));
//            }
//    }


    private static void populateAlertScheduleMap(List<Vaccine> vaccines, Map<String, Pair<String, Boolean>> map) {

        for (Vaccine vaccine : vaccines)
            if (vaccine.getVaccineSeparator() != null && vaccine.getName().contains(vaccine.getVaccineSeparator().trim())) {
                String[] individualVaccines = vaccine.getName().split(vaccine.getVaccineSeparator().trim());

                List<Vaccine> vaccineList = new ArrayList<>();
                for (String individualVaccine : individualVaccines) {
                    Vaccine vaccineClone = new Vaccine();
                    vaccineClone.setName(individualVaccine.trim());
                    vaccineList.add(vaccineClone);
                }
                populateAlertScheduleMap(vaccineList, map);

            } else {
                // TODO: This needs to be fixed because it is a configuration & not a hardcoded string
                map.put(vaccine.name, Pair.create(AppConstants.RegisterTable.CLIENT, false));
            }
    }

//    private static Map<String, Pair<String, Boolean>> getAlertScheduleMap(android.content.Context context) {
//        List<VaccineGroup> vaccines = getVaccineGroups(context);
//
//        Map<String, Pair<String, Boolean>> map = new HashMap<>();
//
//        for (VaccineGroup vaccineGroup : vaccines) {
//            populateAlertScheduleMap(vaccineGroup.vaccines, map);
//        }
//        return map;
//    }

    public static synchronized CovacsApplication getInstance() {
        return (CovacsApplication) mInstance;
    }

//    public static List<VaccineGroup> getVaccineGroups(android.content.Context context) {
//        if (vaccineGroups == null) {
//            vaccineGroups = VaccinatorUtils.getVaccineGroupsFromVaccineConfigFile(context, VaccinatorUtils.vaccines_file);
//        }
//        return vaccineGroups;
//    }


    private void initRepositories() {
//        vaccineRepository();

    }
//
//    private void initOfflineSchedules() {
//        try {
////            List<VaccineGroup> vaccines = VaccinatorUtils.getSupportedVaccines(this);
////            List<Vaccine> specialVaccines = VaccinatorUtils.getSpecialVaccines(this);
////            VaccineSchedule.init(vaccines, specialVaccines, AppConstants.KeyConstants.CHILD);
//        } catch (Exception e) {
//            Timber.e(e, "CovacsApplication --> initOfflineSchedules");
//        }
//    }


//    public VaccineRepository vaccineRepository() {
//        return ImmunizationLibrary.getInstance().vaccineRepository();
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

    @NotNull
    @Override
    public ClientProcessorForJava getClientProcessor() {
        if (clientProcessorForJava == null) {
//            clientProcessorForJava = new AppClientProcessorForJava(getApplicationContext());
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

    public boolean isLastModified() {
        return lastModified;
    }

    public void setLastModified(boolean lastModified) {
        this.lastModified = lastModified;
    }

    public ECSyncHelper getEcSyncHelper() {
        if (ecSyncHelper == null) {
            ecSyncHelper = ECSyncHelper.getInstance(getApplicationContext());
        }
        return ecSyncHelper;
    }

//    @VisibleForTesting
//    protected void fixHardcodedVaccineConfiguration() {
//        VaccineRepo.Vaccine[] vaccines = ImmunizationLibrary.getInstance().getVaccines(AppConstants.KeyConstants.CHILD);
//
//        HashMap<String, VaccineDuplicate> replacementVaccines = new HashMap<>();
//        replacementVaccines.put("BCG 2", new VaccineDuplicate("BCG 2", VaccineRepo.Vaccine.bcg, 1825, 0, 15, AppConstants.KeyConstants.CHILD));
//
//        for (VaccineRepo.Vaccine vaccine : vaccines) {
//            if (replacementVaccines.containsKey(vaccine.display())) {
//                VaccineDuplicate vaccineDuplicate = replacementVaccines.get(vaccine.display());
//                vaccine.setCategory(vaccineDuplicate.category());
//                vaccine.setExpiryDays(vaccineDuplicate.expiryDays());
//                vaccine.setMilestoneGapDays(vaccineDuplicate.milestoneGapDays());
//                vaccine.setPrerequisite(vaccineDuplicate.prerequisite());
//                vaccine.setPrerequisiteGapDays(vaccineDuplicate.prerequisiteGapDays());
//            }
//        }

//        ImmunizationLibrary.getInstance().setVaccines(vaccines, AppConstants.KeyConstants.CHILD);
//    }


    @VisibleForTesting
    public void setVaccineGroups(List<VaccineGroup> vaccines) {
        vaccineGroups = vaccines;
    }

//    public ClientRegisterTypeRepository registerTypeRepository() {
//        if (registerTypeRepository == null) {
//            this.registerTypeRepository = new ClientRegisterTypeRepository();
//        }
//        return this.registerTypeRepository;
//    }
//
//    public ChildAlertUpdatedRepository alertUpdatedRepository() {
//        if (childAlertUpdatedRepository == null) {
//            this.childAlertUpdatedRepository = new ChildAlertUpdatedRepository();
//        }
//        return this.childAlertUpdatedRepository;
//    }

    public RecurringServiceTypeRepository getRecurringServiceTypeRepository() {
        return ImmunizationLibrary.getInstance().recurringServiceTypeRepository();
    }

    public RecurringServiceRecordRepository getRecurringServiceRecordRepository() {
        return ImmunizationLibrary.getInstance().recurringServiceRecordRepository();
    }

//    public CohortRepository cohortRepository() {
//        if (cohortRepository == null) {
//            cohortRepository = new CohortRepository();
//        }
//        return cohortRepository;
//    }
//
//    public CohortPatientRepository cohortPatientRepository() {
//        if (cohortPatientRepository == null) {
//            cohortPatientRepository = new CohortPatientRepository();
//        }
//        return cohortPatientRepository;
//    }
//
//    public CohortIndicatorRepository cohortIndicatorRepository() {
//        if (cohortIndicatorRepository == null) {
//            cohortIndicatorRepository = new CohortIndicatorRepository();
//        }
//        return cohortIndicatorRepository;
//    }
//
//    public CumulativeRepository cumulativeRepository() {
//        if (cumulativeRepository == null) {
//            cumulativeRepository = new CumulativeRepository();
//        }
//        return cumulativeRepository;
//    }
//
//    public CumulativePatientRepository cumulativePatientRepository() {
//        if (cumulativePatientRepository == null) {
//            cumulativePatientRepository = new CumulativePatientRepository();
//        }
//        return cumulativePatientRepository;
//    }
//
//    public CumulativeIndicatorRepository cumulativeIndicatorRepository() {
//        if (cumulativeIndicatorRepository == null) {
//            cumulativeIndicatorRepository = new CumulativeIndicatorRepository();
//        }
//        return cumulativeIndicatorRepository;
//    }

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
}
