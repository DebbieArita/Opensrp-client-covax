package com.example.opensrp_client_covacs.contract;

import android.app.Activity;

import com.example.opensrp_client_covacs.application.CovacsApplication;
import com.example.opensrp_client_covacs.model.NavigationOption;

import java.util.Date;
import java.util.List;

public interface NavigationContract {
    interface Presenter {

        NavigationContract.View getNavigationView();

        void refreshLastSync();

        void displayCurrentUser();

        void sync(Activity activity);

        String getLoggedInUserInitials();

        void refreshNavigationCount(Activity activity);
    }

    interface View {

        void prepareViews(Activity activity);

        void refreshLastSync(Date lastSync);

        void refreshCurrentUser(String name);

        void logout(Activity activity);

        void refreshCount();

    }

    interface Model {

        String getCurrentUser();

        List<NavigationOption> getNavigationItems();
    }

    interface Interactor {

        Date sync();

        void setApplication(CovacsApplication covacsApplication);

        void getRegisterCount(String tableName, InteractorCallback<Integer> callback);

    }

    interface InteractorCallback<T> {
        void onResult(T result);

        void onError(Exception e);
    }
}
