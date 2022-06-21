package com.example.opensrp_client_covacs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.fragment.AdvancedSearchFragment;
import com.example.opensrp_client_covacs.fragment.ChildRegisterFragment;
import com.example.opensrp_client_covacs.presenter.AppChildRegisterPresenter;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;
import org.smartregister.Context;
import org.smartregister.CoreLibrary;
import org.smartregister.adapter.PagerAdapter;
import org.smartregister.client.utils.domain.Form;
import org.smartregister.configurableviews.util.Constants;
import org.smartregister.helper.BottomNavigationHelper;
import org.smartregister.listener.BottomNavigationListener;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.contract.BaseRegisterContract;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChildRegisterActivity extends BaseRegisterActivity implements ChildRegisterContract.View, ChildRegisterContract.ProgressDialogCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_register);

    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        return null;
    }

    @Override
    protected Fragment[] getOtherFragments() {
        return new Fragment[0];
    }

    @Override
    public void startFormActivity(String s, String s1, Map<String, String> map) {

    }

    @Override
    public void startFormActivity(JSONObject jsonObject) {

    }

    @Override
    protected void onActivityResultExtended(int i, int i1, Intent intent) {

    }

    @Override
    public List<String> getViewIdentifiers() {
        return null;
    }

    @Override
    public ChildRegisterContract.Presenter presenter() {
        return null;
    }

    @Override
    public void setActiveMenuItem(int menuItemId) {

    }

    @Override
    public void dissmissProgressDialog() {

    }

    @Override
    public void startRegistration() {

    }
}


