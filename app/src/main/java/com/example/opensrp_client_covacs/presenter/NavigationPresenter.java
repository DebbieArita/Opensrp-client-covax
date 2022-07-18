package com.example.opensrp_client_covacs.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.example.opensrp_client_covacs.contract.NavigationContract;
import com.example.opensrp_client_covacs.interactor.NavigationInteractor;
import com.example.opensrp_client_covacs.job.ScheduleJob;
import com.example.opensrp_client_covacs.model.NavigationModel;
import com.example.opensrp_client_covacs.model.NavigationOption;

import org.smartregister.Context;
import org.smartregister.job.ExtendedSyncServiceJob;
import org.smartregister.job.ImageUploadServiceJob;
import org.smartregister.job.PullUniqueIdsServiceJob;
import org.smartregister.job.SyncLocationsByLevelAndTagsServiceJob;
import org.smartregister.job.SyncServiceJob;
import org.smartregister.job.ValidateSyncDataServiceJob;
import org.smartregister.repository.AllSharedPreferences;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import timber.log.Timber;

public class NavigationPresenter implements NavigationContract.Presenter {

    private final NavigationContract.Model model;
    private final NavigationContract.Interactor interactor;
    private final WeakReference<NavigationContract.View> view;
    private HashMap<String, String> tableMap = new HashMap<>();

    public NavigationPresenter(NavigationContract.View view) {
        this.model = NavigationModel.getInstance();
        this.interactor = NavigationInteractor.getInstance();
        this.view = new WeakReference<>(view);
    }

    @Override
    public NavigationContract.View getNavigationView() {
        return view.get();
    }

    @Override
    public void refreshLastSync() {
        getNavigationView().refreshLastSync(interactor.sync());

    }

    @Override
    public void displayCurrentUser() {
        getNavigationView().refreshCurrentUser(model.getCurrentUser());

    }

    @Override
    public void sync(Activity activity) {
        SyncServiceJob.scheduleJobImmediately(SyncServiceJob.TAG);
        ExtendedSyncServiceJob.scheduleJobImmediately(ExtendedSyncServiceJob.TAG);
        PullUniqueIdsServiceJob.scheduleJobImmediately(PullUniqueIdsServiceJob.TAG);
        ValidateSyncDataServiceJob.scheduleJobImmediately(ValidateSyncDataServiceJob.TAG);
        ImageUploadServiceJob.scheduleJobImmediately(ImageUploadServiceJob.TAG);
        ScheduleJob.scheduleJobImmediately(ScheduleJob.TAG);
        SyncLocationsByLevelAndTagsServiceJob.scheduleJobImmediately(SyncLocationsByLevelAndTagsServiceJob.TAG);
    }

    @Override
    public String getLoggedInUserInitials() {
        try {
            AllSharedPreferences allSharedPreferences = getAllSharedPreferences();
            String preferredName = allSharedPreferences.getANMPreferredName(allSharedPreferences.fetchRegisteredANM());
            if (!TextUtils.isEmpty(preferredName)) {
                String[] initialsArray = preferredName.split(" ");
                String initials = "";
                if (initialsArray.length > 0) {
                    initials = initialsArray[0].substring(0, 1);
                    if (initialsArray.length > 1) {
                        initials = initials + initialsArray[1].substring(0, 1);
                    }
                }
                return initials.toUpperCase();
            }

        } catch (StringIndexOutOfBoundsException exception) {
            Timber.e(exception, "Error fetching initials");
        }
        return null;
    }

    @Override
    public void refreshNavigationCount(final Activity activity) {

        int x = 0;
        while (x < model.getNavigationItems().size()) {
            final NavigationOption navigationOption = model.getNavigationItems().get(x);
            final String navTitle = navigationOption.getMenuTitle();
            if (tableMap.containsKey(navTitle)) {
                interactor.getRegisterCount(tableMap.get(navTitle), new NavigationContract.InteractorCallback<Integer>() {
                    @Override
                    public void onResult(Integer result) {
                        navigationOption.setRegisterCount(result);
                        getNavigationView().refreshCount();
                    }

                    @Override
                    public void onError(Exception e) {
                        // getNavigationView().displayToast(activity, "Error retrieving count for " + tableMap.get(mModel.getNavigationItems().get(finalX).getMenuTitle()));
                        Timber.e("Error retrieving count for %s", tableMap.get(navTitle));
                    }
                });
            } else if (navigationOption.hasRegisterCount()) {
                Timber.e("Error retrieving count for %s, table not defined in 'tableMap'", navTitle);
            }
            x++;
        }
    }

    public AllSharedPreferences getAllSharedPreferences() {
        return Context.getInstance().allSharedPreferences();
    }
}

