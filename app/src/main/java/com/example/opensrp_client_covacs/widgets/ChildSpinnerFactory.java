package com.example.opensrp_client_covacs.widgets;

import android.view.View;

import com.vijay.jsonwizard.fragments.JsonFormFragment;
import com.vijay.jsonwizard.widgets.SpinnerFactory;

import org.json.JSONObject;

public class ChildSpinnerFactory extends SpinnerFactory {
    @Override
    public void genericWidgetLayoutHookback(View view, JSONObject jsonObject, JsonFormFragment formFragment) {

        WidgetUtils.hookupLookup(view, jsonObject, formFragment);
    }
}
