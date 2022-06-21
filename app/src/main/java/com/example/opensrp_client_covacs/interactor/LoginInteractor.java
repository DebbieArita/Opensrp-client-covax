package com.example.opensrp_client_covacs.interactor;

//import org.smartregister.BuildConfig;

import com.example.opensrp_client_covacs.BuildConfig;

import org.smartregister.immunization.job.RecurringServiceJob;
import org.smartregister.immunization.job.VaccineServiceJob;
import org.smartregister.job.PullUniqueIdsServiceJob;
import org.smartregister.job.SyncAllLocationsServiceJob;
import org.smartregister.job.SyncServiceJob;
import org.smartregister.login.interactor.BaseLoginInteractor;
import org.smartregister.view.contract.BaseLoginContract;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class LoginInteractor extends BaseLoginInteractor implements BaseLoginContract.Interactor {

    @Override
    public void login(WeakReference<BaseLoginContract.View> view, String userName, char[] password) {
        //change case to lowercase before login attempt
        super.login(view, userName.toLowerCase(), password);
    }

//    @Override
//    protected void scheduleJobsPeriodically() {
//        //to review
//    }

    public LoginInteractor(BaseLoginContract.Presenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    protected void scheduleJobsPeriodically() {

        VaccineServiceJob.scheduleJob(VaccineServiceJob.TAG, TimeUnit.MINUTES.toMinutes(BuildConfig.DATA_SYNC_DURATION_MINUTES),
                getFlexValue(BuildConfig.DATA_SYNC_DURATION_MINUTES));


        //This will also take care of SyncServiceJob when done
        SyncServiceJob.scheduleJob(SyncServiceJob.TAG, TimeUnit.MINUTES.toMinutes(BuildConfig.DATA_SYNC_DURATION_MINUTES),
                getFlexValue(BuildConfig.DATA_SYNC_DURATION_MINUTES));

        PullUniqueIdsServiceJob.scheduleJob(PullUniqueIdsServiceJob.TAG, TimeUnit.MINUTES.toMinutes(BuildConfig.PULL_UNIQUE_IDS_MINUTES),
                getFlexValue(BuildConfig.PULL_UNIQUE_IDS_MINUTES));


        RecurringServiceJob.scheduleJob(RecurringServiceJob.TAG,
                TimeUnit.MINUTES.toMinutes(BuildConfig.VACCINE_SYNC_PROCESSING_MINUTES),
                getFlexValue(BuildConfig.VACCINE_SYNC_PROCESSING_MINUTES));
    }

    @Override
    protected void scheduleJobsImmediately() {
        SyncServiceJob.scheduleJobImmediately(SyncServiceJob.TAG);
        SyncAllLocationsServiceJob.scheduleJobImmediately(SyncAllLocationsServiceJob.TAG);
        PullUniqueIdsServiceJob.scheduleJobImmediately(PullUniqueIdsServiceJob.TAG);
        RecurringServiceJob.scheduleJobImmediately(RecurringServiceJob.TAG);

    }
}
