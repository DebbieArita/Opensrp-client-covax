package com.example.opensrp_client_covacs.model;

import com.example.opensrp_client_covacs.contract.ChildRegisterFragmentContract;

import org.json.JSONArray;
import org.smartregister.configurableviews.model.Field;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.domain.Response;

import java.util.List;
import java.util.Set;

public class ChildRegisterFragmentModel implements ChildRegisterFragmentContract.Model {
    @Override
    public RegisterConfiguration defaultRegisterConfiguration() {
        return null;
    }

    @Override
    public ViewConfiguration getViewConfiguration(String var1) {
        return null;
    }

    @Override
    public Set<View> getRegisterActiveColumns(String var1) {
        return null;
    }

    @Override
    public String countSelect(String var1) {
        return null;
    }

    @Override
    public String mainSelect(String var1) {
        return null;
    }

    @Override
    public String getFilterText(List<Field> var1, String var2) {
        return null;
    }

    @Override
    public String getSortText(Field var1) {
        return null;
    }

    @Override
    public JSONArray getJsonArray(Response<String> var1) {
        return null;
    }
}
