package com.example.opensrp_client_covacs.fragment;

import android.os.Bundle;

import com.example.opensrp_client_covacs.interactor.ChildFormInteractor;
import com.example.opensrp_client_covacs.presenter.ChildFormFragmentPresenter;
import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.fragments.JsonWizardFormFragment;

public class ChildFormFragment extends JsonWizardFormFragment {

    public static final String TAG = ChildFormFragment.class.getName();

    public static ChildFormFragment getFormFragment(String stepName) {
        ChildFormFragment jsonFormFragment = new ChildFormFragment();
        Bundle bundle = new Bundle();
        bundle.putString(JsonFormConstants.JSON_FORM_KEY.STEPNAME, stepName);
        jsonFormFragment.setArguments(bundle);
        return jsonFormFragment;
    }

    @Override
    protected ChildFormFragmentPresenter createPresenter() {
        return new ChildFormFragmentPresenter(this, ChildFormInteractor.getChildInteractorInstance());
    }
}

