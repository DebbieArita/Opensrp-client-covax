package com.example.opensrp_client_covacs.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.opensrp_client_covacs.application.CovacsApplication;

import org.smartregister.CoreLibrary;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;

public class SchedulesIntentService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public SchedulesIntentService(String name) {
        super("ScheduleIntentService");
    }

    public boolean isSyncing() {
        return CoreLibrary.getInstance().isPeerToPeerProcessing() || SyncStatusBroadcastReceiver.getInstance().isSyncing();
    }

    public CovacsApplication getCovacsApplication() {
        return (CovacsApplication) CovacsApplication.getInstance();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (isSyncing())
            return;

        if (getCovacsApplication().allowLazyProcessing())
            processLazyEvents();
    }

    private void processLazyEvents() {
        if (isSyncing())
            return;

    }
}
