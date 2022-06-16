package com.example.opensrp_client_covacs.contract;

import androidx.annotation.NonNull;

import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.domain.tag.FormTag;

import java.util.List;
import java.util.Map;

public interface ChildRegisterContract {

    public interface ProgressDialogCallback {
        void dissmissProgressDialog();
    }

    public interface InteractorCallBack {
        void onUniqueIdFetched(Triple<String, Map<String, String>, String> var1, String var2);

        void onNoUniqueId();

        void onRegistrationSaved(boolean var1);
    }

//    public interface Interactor {
//        void onDestroy(boolean var1);
//
//        void getNextUniqueId(Triple<String, Map<String, String>, String> var1, ChildRegisterContract.InteractorCallBack var2);
//
//        void saveRegistration(List<ChildEventClient> var1, String var2, UpdateRegisterParams var3, ChildRegisterContract.InteractorCallBack var4);
//
//        void removeChildFromRegister(String var1, String var2);
//
//        void processWeight(@NonNull Map<String, String> var1, @NonNull String var2, @NonNull UpdateRegisterParams var3, @NonNull JSONObject var4) throws JSONException;
//
//        void processHeight(@NonNull Map<String, String> var1, @NonNull String var2, @NonNull UpdateRegisterParams var3, @NonNull JSONObject var4) throws JSONException;
//
//        void processTetanus(@NonNull Map<String, String> var1, @NonNull String var2, @NonNull UpdateRegisterParams var3, @NonNull JSONObject var4) throws JSONException;
//
//        boolean isClientMother(@NonNull Map<String, String> var1);
//    }

    public interface Model {
        void registerViewConfigurations(List<String> var1);

        void unregisterViewConfiguration(List<String> var1);

        void saveLanguage(String var1);

        String getLocationId(String var1);

//        List<ChildEventClient> processRegistration(String var1, FormTag var2);

        JSONObject getFormAsJson(String var1, String var2, String var3, Map<String, String> var4) throws Exception;

        String getInitials();
    }

    public interface Presenter extends org.smartregister.view.contract.BaseRegisterContract.Presenter {
        void saveLanguage(String var1);

        void startForm(String var1, String var2, String var3, String var4) throws Exception;

        void startForm(String var1, String var2, Map<String, String> var3, String var4) throws Exception;

//        void saveForm(String var1, UpdateRegisterParams var2);

        void saveOutOfCatchmentService(String var1, ChildRegisterContract.ProgressDialogCallback var2);

        void closeChildRecord(String var1);
    }

    public interface View extends org.smartregister.view.contract.BaseRegisterContract.View {
        ChildRegisterContract.Presenter presenter();
    }
}
