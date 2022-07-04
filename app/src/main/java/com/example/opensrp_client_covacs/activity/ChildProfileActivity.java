package com.example.opensrp_client_covacs.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.opensrp_client_covacs.util.Utils;

import org.smartregister.view.activity.BaseProfileActivity;

public class ChildProfileActivity extends BaseProfileActivity {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Utils.showToast(this, "In the profile page!!");

    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    protected ViewPager setupViewPager(ViewPager viewPager) {
        return null;
    }

    @Override
    protected void fetchProfileData() {

    }
}
