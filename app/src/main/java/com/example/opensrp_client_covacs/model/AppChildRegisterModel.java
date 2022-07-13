package com.example.opensrp_client_covacs.model;

import static com.example.opensrp_client_covacs.util.ChildJsonFormUtils.ENCOUNTER_TYPE;

import android.content.Context;

import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.util.ChildJsonFormUtils;
import com.example.opensrp_client_covacs.util.Utils;

import org.json.JSONObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.domain.tag.FormTag;
import org.smartregister.location.helper.LocationHelper;
import org.smartregister.util.FormUtils;

import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class AppChildRegisterModel implements ChildRegisterContract.Model {

    private FormUtils formUtils;


    @Override
    public void registerViewConfigurations(List<String> viewIdentifiers) {
        ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().registerViewConfigurations(viewIdentifiers);
    }

    @Override
    public void unregisterViewConfiguration(List<String> viewIdentifiers) {
        ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().unregisterViewConfiguration(viewIdentifiers);
    }

    @Override
    public void saveLanguage(String language) {
        //empty block

    }

    @Override
    public String getLocationId(String locationName) {
        return LocationHelper.getInstance().getOpenMrsLocationId(locationName);
    }

    @Override
    public JSONObject getFormAsJson(Context context, String formName, String entityId) throws Exception {
//        JSONObject form = getFormUtils().getFormJson(formName);
//        if (form == null) {
//            return null;
//        }
        return ChildJsonFormUtils.getJson(context, formName, entityId);
    }

    @Override
    public JSONObject getEditFormAsJson(Context context, String formName, String updateEventType, String entityId, Map<String, String> valueMap) throws Exception {
        JSONObject formJsonObject = getFormAsJson(context, formName, entityId);
        formJsonObject.put(ENCOUNTER_TYPE, updateEventType);
        ChildJsonFormUtils.populateJsonForm(formJsonObject, valueMap);
        return formJsonObject;
    }


    @Override
    public String getInitials() {
        return Utils.getUserInitials();
    }
}
