package com.example.opensrp_client_covacs.model;

import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.util.Utils;

import org.json.JSONObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.domain.tag.FormTag;
import org.smartregister.location.helper.LocationHelper;

import java.util.List;
import java.util.Map;

public class AppChildRegisterModel implements ChildRegisterContract.Model {
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

//    @Override
//    public List<ChildEventClient> processRegistration(String jsonString, FormTag formTag) {
//        return null;
//    }

    @Override
    public JSONObject getFormAsJson(String formName, String entityId, String currentLocationId, Map<String, String> metadata) throws Exception {
        return null;
    }

    @Override
    public String getInitials() {
        return Utils.getUserInitials();
    }
}
