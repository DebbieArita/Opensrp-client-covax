package com.example.opensrp_client_covacs.model;

import com.example.opensrp_client_covacs.contract.ChildRegisterContract;

import org.json.JSONObject;
import org.smartregister.domain.tag.FormTag;

import java.util.List;
import java.util.Map;

public class AppChildRegisterModel implements ChildRegisterContract.Model {
    @Override
    public void registerViewConfigurations(List<String> viewIdentifiers) {

    }

    @Override
    public void unregisterViewConfiguration(List<String> viewIdentifiers) {

    }

    @Override
    public void saveLanguage(String language) {

    }

    @Override
    public String getLocationId(String locationName) {
        return null;
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
        return null;
    }
}
