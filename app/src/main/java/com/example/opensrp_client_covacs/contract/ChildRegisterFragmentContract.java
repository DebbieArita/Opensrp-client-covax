package com.example.opensrp_client_covacs.contract;

import com.example.opensrp_client_covacs.cursor.AdvancedMatrixCursor;

import org.json.JSONArray;
import org.smartregister.configurableviews.model.Field;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.domain.Response;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

import java.util.List;
import java.util.Set;

public interface ChildRegisterFragmentContract {
    interface Model {
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

    interface Presenter extends org.smartregister.view.contract.BaseRegisterFragmentContract.Presenter {
        void updateSortAndFilter(List<Field> var1, Field var2);

        String getMainCondition();

        String getDefaultSortQuery();
    }

    interface View extends BaseRegisterFragmentContract.View {
        void initializeAdapter(Set<org.smartregister.configurableviews.model.View> var1);

        void recalculatePagination(AdvancedMatrixCursor matrixCursor);

        ChildRegisterFragmentContract.Presenter presenter();
    }
}
