package com.example.opensrp_client_covacs.widgets;

import android.view.View;

import com.vijay.jsonwizard.fragments.JsonFormFragment;
import com.vijay.jsonwizard.widgets.CheckBoxFactory;

import org.json.JSONObject;

public class ChildCheckboxTextFactory extends CheckBoxFactory {
    @Override
    public void genericWidgetLayoutHookback(View view, JSONObject jsonObject, JsonFormFragment formFragment) {

        WidgetUtils.hookupLookup(view, jsonObject, formFragment);
    }
}
