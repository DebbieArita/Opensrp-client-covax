package com.example.opensrp_client_covacs.job;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.opensrp_client_covacs.util.AppConstants;

import org.smartregister.immunization.service.intent.RecurringIntentService;
import org.smartregister.immunization.service.intent.VaccineIntentService;
import org.smartregister.job.BaseJob;

public class VaccineRecurringServiceJob extends BaseJob {

    public static final String TAG = "VaccineRecurringServiceJob";

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        Intent intent = new Intent(getApplicationContext(), VaccineIntentService.class);
        getApplicationContext().startService(intent);
        Intent intent1 = new Intent(getApplicationContext(), RecurringIntentService.class);
        getApplicationContext().startService(intent1);
        return params.getExtras().getBoolean(AppConstants.INTENT_KEY.TO_RESCHEDULE, false) ? Result.RESCHEDULE : Result.SUCCESS;
    }
}
