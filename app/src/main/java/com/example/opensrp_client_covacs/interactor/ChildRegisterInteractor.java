package com.example.opensrp_client_covacs.interactor;

import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.util.ChildJsonFormUtils;
import com.example.opensrp_client_covacs.util.Utils;

import org.smartregister.clientandeventmodel.Event;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.util.AppExecutors;


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

//    @Override
//    public void getNextUniqueId(Triple<String, Map<String, String>, String> var1, ChildRegisterContract.InteractorCallBack var2) {
//
//    }

    @Override
    public void saveRegistration(String jsonString, String table, ChildRegisterContract.InteractorCallBack callBack) {
//        Runnable runnable = () -> {
//            try {
//                AllSharedPreferences allSharedPreferences = Utils.context().allSharedPreferences();
//                Event baseEvent = ChildJsonFormUtils.processJsonForm(allSharedPreferences, jsonString, table);
//
//                NCUtils.addEvent(allSharedPreferences, baseEvent);
//                NCUtils.startClientProcessing();
//
//            } catch (Exception ex) {
//                Timber.e(ex);
//            }
//
//            appExecutors.mainThread().execute(callback::onRegistrationSaved);
//        };
//        appExecutors.diskIO().execute(runnable);

    }


}
