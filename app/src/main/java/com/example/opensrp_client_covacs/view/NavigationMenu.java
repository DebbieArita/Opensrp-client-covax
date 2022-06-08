package com.example.opensrp_client_covacs.view;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.example.opensrp_client_covacs.contract.NavigationContract;
import com.example.opensrp_client_covacs.util.AppConstants;

import org.smartregister.domain.FetchStatus;
import org.smartregister.receiver.SyncStatusBroadcastReceiver;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Locale;

public class NavigationMenu implements NavigationContract.View, SyncStatusBroadcastReceiver.SyncStatusListener{

    private static NavigationMenu instance;
    private static WeakReference<Activity> activityWeakReference;
//    private static final Locale ARABIC_LOCALE = new Locale(AppConstants.Locale.ARABIC_LOCALE);
    private LinearLayout syncMenuItem;
    private LinearLayout outOfAreaMenu;
    private LinearLayout registerView;
    private LinearLayout reportView;
    private LinearLayout droputReportsView;
    private LinearLayout coverageReportsView;
    private LinearLayout stockControlView;
    private TextView loggedInUserTextView;
    private TextView userInitialsTextView;
    private TextView syncTextView;
    private TextView logoutButton;
    private NavigationContract.Presenter mPresenter;
    private DrawerLayout drawer;
    private ImageButton cancelButton;
    private Spinner languageSpinner;
    private static String[] langArray;

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
