package com.example.opensrp_client_covacs.view;

import android.app.Activity;

import com.example.opensrp_client_covacs.contract.NavigationContract;

import org.smartregister.domain.FetchStatus;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;

import java.util.Date;

public class NavigationMenu implements NavigationContract.View, SyncStatusBroadcastReceiver.SyncStatusListener{

    @Override
    public void onSyncStart() {

    }

    @Override
    public void onSyncInProgress(FetchStatus fetchStatus) {

    }

    @Override
    public void onSyncComplete(FetchStatus fetchStatus) {

    }

    @Override
    public void prepareViews(Activity activity) {

    }

    @Override
    public void refreshLastSync(Date lastSync) {

    }

    @Override
    public void refreshCurrentUser(String name) {

    }

    @Override
    public void logout(Activity activity) {

    }
}
