package com.example.opensrp_client_covacs.watchers;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.opensrp_client_covacs.domain.EntityLookUp;
import com.example.opensrp_client_covacs.fragment.ChildFormFragment;
import com.vijay.jsonwizard.fragments.JsonFormFragment;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.commonregistry.CommonPersonObject;
import org.smartregister.event.Listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookUptextWatcher implements TextWatcher {

    private static Map<String, EntityLookUp> lookUpMap;

    private final View mView;
    private final JsonFormFragment formFragment;
    private final String mEntityId;


//    public LookUpTextWatcher(JsonFormFragment formFragment, View view, String entityId) {
//        this.formFragment = formFragment;
//        mView = view;
//        mEntityId = entityId;
//        lookUpMap = new HashMap<>();
//
//    }

    public LookUptextWatcher(View mView, JsonFormFragment formFragment, String mEntityId) {
        this.mView = mView;
        this.formFragment = formFragment;
        this.mEntityId = mEntityId;
        lookUpMap = new HashMap<>();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = (String) mView.getTag(com.vijay.jsonwizard.R.id.raw_value);

        if (text == null) {
            text = editable.toString();
        }

        String key = (String) mView.getTag(com.vijay.jsonwizard.R.id.key);

        boolean afterLookUp = (Boolean) mView.getTag(com.vijay.jsonwizard.R.id.after_look_up);
        if (afterLookUp) {
            mView.setTag(com.vijay.jsonwizard.R.id.after_look_up, false);
            return;
        }

        EntityLookUp entityLookUp = new EntityLookUp();
        if (lookUpMap.containsKey(mEntityId)) {
            entityLookUp = lookUpMap.get(mEntityId);
        }

        if (StringUtils.isBlank(text)) {
            if (entityLookUp.containsKey(key)) {
                entityLookUp.remove(key);
            }
        } else {
            entityLookUp.put(key, text);
        }

        lookUpMap.put(mEntityId, entityLookUp);


        Listener<Map<CommonPersonObject, List<CommonPersonObject>>> listener = null;
        if (formFragment instanceof ChildFormFragment) {
            ChildFormFragment childFormFragment = (ChildFormFragment) formFragment;
//            listener = childFormFragment.motherLookUpListener();
        }

//        if (mEntityId.equalsIgnoreCase(Constants.KEY.MOTHER)) {
//            initiateLookUp(listener);
//        }

    }

//    protected void initiateLookUp(Listener<Map<CommonPersonObject, List<CommonPersonObject>>> listener) {
//
//    }
}
