package com.example.opensrp_client_covacs.interactor;

import static org.smartregister.util.Utils.getAllSharedPreferences;

import com.example.opensrp_client_covacs.application.CovacsApplication;
import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.domain.ChildEventClient;
import com.example.opensrp_client_covacs.domain.UpdateRegisterParams;
import com.example.opensrp_client_covacs.event.ClientDirtyFlagEvent;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.util.ChildJsonFormUtils;
import com.example.opensrp_client_covacs.util.Utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.clientandeventmodel.Client;
import org.smartregister.clientandeventmodel.Event;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.repository.EventClientRepository;
import org.smartregister.repository.UniqueIdRepository;
import org.smartregister.sync.ClientProcessorForJava;
import org.smartregister.sync.helper.ECSyncHelper;
import org.smartregister.util.AppExecutors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import timber.log.Timber;


public class ChildRegisterInteractor implements ChildRegisterContract.Interactor {

    private final AppExecutors appExecutors;

    public ChildRegisterInteractor() {
        this(new AppExecutors());
    }

    public ChildRegisterInteractor(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    @Override
    public void onDestroy(boolean var1) {

    }

    @Override
    public void saveRegistration(List<ChildEventClient> childEventClientList, String jsonString,
                                 UpdateRegisterParams updateRegisterParams, ChildRegisterContract.InteractorCallBack callBack) {
        Runnable runnable = () -> {
            saveRegistration(childEventClientList, jsonString, updateRegisterParams);
            appExecutors.mainThread().execute(() -> callBack.onRegistrationSaved(updateRegisterParams.isEditMode()));
        };

        appExecutors.diskIO().execute(runnable);

    }

    public void saveRegistration(List<ChildEventClient> childEventClientList, String jsonString, UpdateRegisterParams params) {
        try {
            List<String> currentFormSubmissionIds = new ArrayList<>();

            for (int i = 0; i < childEventClientList.size(); i++) {
                try {
                    ChildEventClient childEventClient = childEventClientList.get(i);
                    Client baseClient = childEventClient.getClient();
                    Event baseEvent = childEventClient.getEvent();

                    if (baseClient != null) {
                        JSONObject clientJson = new JSONObject(ChildJsonFormUtils.gson.toJson(baseClient));
                        if (params.isEditMode()) {
                            try{
                                ChildJsonFormUtils.mergeAndSaveClient(baseClient);
                            } catch (Exception e) {
                                Timber.e(e, "ChildRegisterInteractor --> mergeAndSaveClient");
                            }
                        } else {
                            addClient(jsonString, params, baseClient, clientJson);
                        }
                    }

                    addEvent(params, currentFormSubmissionIds, baseEvent);
                    updateOpenSRPId(jsonString, params, baseClient);

                    //Broadcast after all processing is done
                    if (AppConstants.KeyConstants.CHILD.equals(baseEvent.getEntityType())) {
                        Utils.postEvent(new ClientDirtyFlagEvent(baseClient.getBaseEntityId(), baseEvent.getEventType()));
                    }
                } catch (Exception e) {
                    Timber.e(e, "ChildRegisterInteractor --> saveRegistration loop");
                }
            }

            long lastSyncTimeStamp = getAllSharedPreferences().fetchLastUpdatedAtDate(0);
            Date lastSyncDate = new Date(lastSyncTimeStamp);
            CovacsApplication.getInstance().getClientProcessorForJava().processClient(getSyncHelper().getEvents(currentFormSubmissionIds));

            getAllSharedPreferences().saveLastUpdatedAtDate(lastSyncDate.getTime());
        } catch (Exception e) {
            Timber.e(e, "ChildRegisterInteractor --> saveRegistration");
        }
    }

    private void addClient(String jsonString, UpdateRegisterParams params, Client baseClient, JSONObject clientJson) throws JSONException {
        getSyncHelper().addClient(baseClient.getBaseEntityId(), clientJson);
    }


    public ECSyncHelper getSyncHelper() {
        return CovacsApplication.getInstance().getEcSyncHelper();
    }

    private void addEvent(UpdateRegisterParams params, List<String> currentFormSubmissionIds, Event baseEvent) throws JSONException {
        if (baseEvent != null) {
            JSONObject eventJson = new JSONObject(ChildJsonFormUtils.gson.toJson(baseEvent));
            getSyncHelper().addEvent(baseEvent.getBaseEntityId(), eventJson, params.getStatus());
            currentFormSubmissionIds.add(eventJson.getString(EventClientRepository.event_column.formSubmissionId.toString()));
        }
    }

    private void updateOpenSRPId(String jsonString, UpdateRegisterParams params, Client baseClient) {
        if (params.isEditMode()) {
            // Unassign current OPENSRP ID
            if (baseClient != null) {
                try {
                    String newOpenSRPId = baseClient.getIdentifier(ChildJsonFormUtils.ZEIR_ID).replace("-", "");
                    String currentOpenSRPId = ChildJsonFormUtils.getString(jsonString, ChildJsonFormUtils.CURRENT_ZEIR_ID).replace("-", "");
                    if (!newOpenSRPId.equals(currentOpenSRPId)) {
                        //OPENSRP ID was changed
                        getUniqueIdRepository().open(currentOpenSRPId);
                    }
                } catch (Exception e) {//might crash if M_ZEIR
                    Timber.d(e, "ChildRegisterInteractor --> unassign opensrp id");
                }
            }
        } else {
            if (baseClient != null) {
                //mark OPENSRP ID as used
                markUniqueIdAsUsed(baseClient.getIdentifier(ChildJsonFormUtils.ZEIR_ID));
            }
        }
    }

    private void markUniqueIdAsUsed(String openSrpId) {
        if (StringUtils.isNotBlank(openSrpId))
            getUniqueIdRepository().close(openSrpId);
    }

    public UniqueIdRepository getUniqueIdRepository() {
        return CovacsApplication.getInstance().getUniqueIdRepository();
    }

    public AllSharedPreferences getAllSharedPreferences() {
        return Utils.context().allSharedPreferences();
    }

    public ClientProcessorForJava getClientProcessorForJava() {
        return CovacsApplication.getInstance().getClientProcessorForJava();
    }


//    @Override
//    public void getNextUniqueId(Triple<String, Map<String, String>, String> var1, ChildRegisterContract.InteractorCallBack var2) {
//
//    }

//    @Override
//    public void saveRegistration(String jsonString, String table, ChildRegisterContract.InteractorCallBack callBack) {
//
//
//    }


    }
