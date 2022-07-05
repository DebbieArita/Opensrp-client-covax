package com.example.opensrp_client_covacs.interactor;

import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.domain.ChildEventClient;
import com.example.opensrp_client_covacs.domain.UpdateRegisterParams;

import org.apache.commons.lang3.tuple.Triple;

import java.util.List;
import java.util.Map;

public class ChildRegisterInteractor implements ChildRegisterContract.Interactor {
    @Override
    public void onDestroy(boolean var1) {

    }

    @Override
    public void getNextUniqueId(Triple<String, Map<String, String>, String> var1, ChildRegisterContract.InteractorCallBack var2) {

    }

    @Override
    public void saveRegistration(List<ChildEventClient> childEventClientList, String jsonString,
                                 UpdateRegisterParams updateRegisterParams,
                                 ChildRegisterContract.InteractorCallBack callBack) {

        //TODO Save-Registration...
    }
}
