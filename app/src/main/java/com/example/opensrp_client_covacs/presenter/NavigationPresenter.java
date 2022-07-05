package com.example.opensrp_client_covacs.presenter;

import android.app.Activity;

import com.example.opensrp_client_covacs.contract.NavigationContract;
import com.example.opensrp_client_covacs.interactor.NavigationInteractor;
import com.example.opensrp_client_covacs.model.NavigationModel;

import java.lang.ref.WeakReference;

public class NavigationPresenter implements NavigationContract.Presenter {

    private final NavigationContract.Model model;
    private final NavigationContract.Interactor interactor;
    private final WeakReference<NavigationContract.View> view;

    public NavigationPresenter(NavigationContract.View view) {
        this.model = NavigationModel.getInstance();
        this.interactor = NavigationInteractor.getInstance();
        this.view = new WeakReference<>(view);
    }

    @Override
    public NavigationContract.View getNavigationView() {
        return null;
    }

    @Override
    public void refreshLastSync() {

    }

    @Override
    public void displayCurrentUser() {

    }

    @Override
    public void sync(Activity activity) {

    }

    @Override
    public String getLoggedInUserInitials() {
        return null;
    }
}
