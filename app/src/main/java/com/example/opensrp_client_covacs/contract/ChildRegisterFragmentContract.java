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

        ViewConfiguration getViewConfiguration(String viewConfigurationIdentifier);

        Set<org.smartregister.configurableviews.model.View> getRegisterActiveColumns(String viewConfigurationIdentifier);

        String countSelect(String mainCondition);

        String mainSelect(String mainCondition);

        String getFilterText(List<Field> filterList, String filter);

        String getSortText(Field sortField);

//        AdvancedMatrixCursor createMatrixCursor(Response<String> var1);

        JSONArray getJsonArray(Response<String> response);
    }

    interface Presenter extends org.smartregister.view.contract.BaseRegisterFragmentContract.Presenter {
        void updateSortAndFilter(List<Field> filterList, Field sortField);

        String getMainCondition();

        String getDefaultSortQuery();
    }

    interface View extends BaseRegisterFragmentContract.View {
        void initializeAdapter(Set<org.smartregister.configurableviews.model.View> visibleColumns);

        void recalculatePagination(AdvancedMatrixCursor matrixCursor);

        ChildRegisterFragmentContract.Presenter presenter();
    }
}
