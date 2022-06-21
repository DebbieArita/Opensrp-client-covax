package com.example.opensrp_client_covacs.contract;

import org.json.JSONArray;
import org.smartregister.configurableviews.model.Field;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.domain.Response;

import java.util.List;
import java.util.Set;

public interface ChildRegisterFragmentContract {
    public interface Model {
        RegisterConfiguration defaultRegisterConfiguration();

        ViewConfiguration getViewConfiguration(String var1);

        Set<org.smartregister.configurableviews.model.View> getRegisterActiveColumns(String var1);

        String countSelect(String var1);

        String mainSelect(String var1);

        String getFilterText(List<Field> var1, String var2);

        String getSortText(Field var1);

//        AdvancedMatrixCursor createMatrixCursor(Response<String> var1);

        JSONArray getJsonArray(Response<String> var1);
    }

    public interface Presenter extends org.smartregister.view.contract.BaseRegisterFragmentContract.Presenter {
        void updateSortAndFilter(List<Field> var1, Field var2);

        String getMainCondition();

        String getDefaultSortQuery();
    }

    public interface View extends org.smartregister.view.contract.BaseRegisterFragmentContract.View {
        void initializeAdapter(Set<org.smartregister.configurableviews.model.View> var1);

//        void recalculatePagination(AdvancedMatrixCursor var1);

        ChildRegisterFragmentContract.Presenter presenter();
    }
}
