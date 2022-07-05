package com.example.opensrp_client_covacs.interactor;

import com.example.opensrp_client_covacs.contract.NavigationContract;

import java.util.Date;

public class NavigationInteractor implements NavigationContract.Interactor {

    private static NavigationInteractor instance;

    public static NavigationInteractor getInstance() {
        if (instance == null)
            instance = new NavigationInteractor();

        return instance;
    }

    @Override
    public Date sync() {
        return null;
    }
}
