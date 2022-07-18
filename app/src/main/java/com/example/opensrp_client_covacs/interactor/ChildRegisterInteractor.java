package com.example.opensrp_client_covacs.interactor;

import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.domain.ChildEventClient;
import com.example.opensrp_client_covacs.domain.UpdateRegisterParams;
import com.example.opensrp_client_covacs.util.ChildJsonFormUtils;
import com.example.opensrp_client_covacs.util.Utils;

import org.smartregister.clientandeventmodel.Event;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.util.AppExecutors;

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

//    @Override
//    public void saveRegistration(List<ChildEventClient> childEventClientList, String jsonString, UpdateRegisterParams updateRegisterParams, ChildRegisterContract.InteractorCallBack callBack) {
//
//    }

//    @Override
//    public void getNextUniqueId(Triple<String, Map<String, String>, String> var1, ChildRegisterContract.InteractorCallBack var2) {
//
//    }

    @Override
    public void saveRegistration(String jsonString, String table, ChildRegisterContract.InteractorCallBack callBack) {


    }


}
