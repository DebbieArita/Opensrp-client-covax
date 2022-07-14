package com.example.opensrp_client_covacs.presenter;

import com.example.opensrp_client_covacs.util.AppConstants;
import com.vijay.jsonwizard.fragments.JsonFormFragment;
import com.vijay.jsonwizard.interactors.JsonFormInteractor;
import com.vijay.jsonwizard.presenters.JsonFormFragmentPresenter;

public class ChildFormFragmentPresenter extends JsonFormFragmentPresenter {
    public ChildFormFragmentPresenter(JsonFormFragment formFragment, JsonFormInteractor jsonFormInteractor) {
        super(formFragment, jsonFormInteractor);
    }

    @Override
    public void setUpToolBar() {
        super.setUpToolBar();
    }


    public boolean intermediatePage() {
        return this.mStepDetails != null && this.mStepDetails.has(AppConstants.JSON_FORM_EXTRA.NEXT);
    }
}
