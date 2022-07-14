package com.example.opensrp_client_covacs.contract;

import android.content.Context;

import com.example.opensrp_client_covacs.domain.ChildEventClient;
import com.example.opensrp_client_covacs.domain.UpdateRegisterParams;

import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONObject;
import org.smartregister.view.contract.BaseRegisterContract;

import java.util.List;
import java.util.Map;

public interface ChildRegisterContract {

    interface View extends BaseRegisterContract.View {
        ChildRegisterContract.Presenter presenter();

        void setActiveMenuItem(int menuItemId);

        void onRegistrationSaved();

    }


    interface ProgressDialogCallback {
        void dissmissProgressDialog();
    }


    interface InteractorCallBack {
//        void onUniqueIdFetched(Triple<String, Map<String, String>, String> var1, String var2);
//
//        void onNoUniqueId();

        void onRegistrationSaved();
    }


    interface Interactor {
        void onDestroy(boolean isChangingConfiguration);

//        void getNextUniqueId(Triple<String, Map<String, String>, String> triple, ChildRegisterContract.InteractorCallBack callBack);

        void saveRegistration(final String jsonString, String table, InteractorCallBack callBack);


//        void saveRegistration(final List<ChildEventClient> childEventClientList,
//                              final String jsonString,
//                              UpdateRegisterParams updateRegisterParams,
//                              ChildRegisterContract.InteractorCallBack callBack);

//        void removeChildFromRegister(String var1, String var2);
    }

    interface Presenter extends org.smartregister.view.contract.BaseRegisterContract.Presenter {

        View getView();

        void startForm(String formName, String entityId) throws Exception;

        void startEditForm(String formName, String updateEventType, String entityId, Map<String, String> valueMap) throws Exception;

        void saveForm(String jsonString, String table);

//        void saveLanguage(String var1);

//        void closeChildRecord(String var1);
    }


    interface Model {

        void registerViewConfigurations(List<String> viewIdentifiers);

        void unregisterViewConfiguration(List<String> viewIdentifiers);

        void saveLanguage(String language);

        String getLocationId(String locationName);

//        List<ChildEventClient> processRegistration(String jsonString, FormTag formTag);

        JSONObject getFormAsJson(Context context, String formName, String entityId) throws Exception;

        JSONObject getEditFormAsJson(Context context, String formName, String updateEventType, String entityId, Map<String, String> valueMap) throws Exception;


//        JSONObject getFormAsJson(String formName, String entityId, String currentLocationId, Map<String, String> metadata) throws Exception;

        String getInitials();

    }
}
