package com.example.opensrp_client_covacs.widgets;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.watchers.LookUptextWatcher;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vijay.jsonwizard.fragments.JsonFormFragment;
import com.vijay.jsonwizard.widgets.EditTextFactory;

import org.json.JSONObject;

public class ChildEditTextFactory extends EditTextFactory {
    @Override
    public void attachLayout(String stepName, Context context, JsonFormFragment formFragment, JSONObject jsonObject,
                             MaterialEditText editText, ImageView editable) throws Exception {
        super.attachLayout(stepName, context, formFragment, jsonObject, editText, editable);

        WidgetUtils.hookupLookup(editText, jsonObject, formFragment);

        if (jsonObject.has(AppConstants.KeyConstants.LOOK_UP) && jsonObject.get(AppConstants.KeyConstants.LOOK_UP).toString().equalsIgnoreCase(Boolean.TRUE.toString())) {

            String entityId = jsonObject.getString(AppConstants.KeyConstants.BASE_ENTITY_ID);
            editText.addTextChangedListener(new LookUptextWatcher(editText, formFragment, entityId));
            editText.setTag(com.vijay.jsonwizard.R.id.after_look_up, false);
        }
    }
}
