package com.example.opensrp_client_covacs.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.presenter.LoginPresenter;
import com.example.opensrp_client_covacs.util.AppConstants;

import org.smartregister.location.helper.LocationHelper;
import org.smartregister.view.activity.BaseLoginActivity;
import org.smartregister.view.contract.BaseLoginContract;

public class LoginActivity extends BaseLoginActivity implements BaseLoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLoginPresenter.processViewCustomizations();
        if (!mLoginPresenter.isUserLoggedOut()) {
            goToHome(false);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initializePresenter() {
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void goToHome(boolean remote) {
        if (remote) {
            LocationHelper.getInstance().locationIdsFromHierarchy();
        }

        if (mLoginPresenter.isServerSettingsSet()) {
            Intent intent = new Intent(this, ChildRegisterActivity.class);

            intent.putExtra(AppConstants.IntentKeyUtil.IS_REMOTE_LOGIN, remote);
            startActivity(intent);
        }

        finish();
    }

}
