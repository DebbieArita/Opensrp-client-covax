package com.example.opensrp_client_covacs.widgets;

import android.content.Context;
import android.widget.TextView;

import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.watchers.LookUptextWatcher;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vijay.jsonwizard.fragments.JsonFormFragment;
import com.vijay.jsonwizard.widgets.DatePickerFactory;

import org.json.JSONException;
import org.json.JSONObject;

import timber.log.Timber;

public class ChildDatePickerFactory extends DatePickerFactory {
    @Override
    public void attachLayout(String stepName, final Context context, JsonFormFragment formFragment, JSONObject jsonObject,
                             final MaterialEditText editText, final TextView duration) {
        super.attachLayout(stepName, context, formFragment, jsonObject, editText, duration);

        WidgetUtils.hookupLookup(editText, jsonObject, formFragment);

        try {
            if (jsonObject.has(AppConstants.KeyConstants.LOOK_UP) && jsonObject.get(AppConstants.KeyConstants.LOOK_UP).toString().equalsIgnoreCase(Boolean.TRUE.toString())) {
                String entityId = jsonObject.getString(AppConstants.KeyConstants.BASE_ENTITY_ID);
                editText.addTextChangedListener(new LookUptextWatcher(editText, formFragment, entityId));
                editText.setTag(com.vijay.jsonwizard.R.id.after_look_up, false);
            }
        } catch (JSONException e) {
            Timber.e(e, getClass().getName(), e.toString());
        }
    }
}
