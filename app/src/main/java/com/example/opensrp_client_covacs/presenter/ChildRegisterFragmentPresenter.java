package com.example.opensrp_client_covacs.presenter;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.contract.ChildRegisterFragmentContract;
import com.example.opensrp_client_covacs.cursor.AdvancedMatrixCursor;
import com.example.opensrp_client_covacs.fragment.ChildRegisterFragment;
import com.example.opensrp_client_covacs.model.ChildRegisterFragmentModel;
import com.example.opensrp_client_covacs.util.Utils;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.configurableviews.model.Field;
import org.smartregister.configurableviews.model.RegisterConfiguration;
import org.smartregister.configurableviews.model.View;
import org.smartregister.configurableviews.model.ViewConfiguration;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ChildRegisterFragmentPresenter implements ChildRegisterFragmentContract.Presenter {

    protected WeakReference<ChildRegisterFragmentContract.View> viewReference;
    protected ChildRegisterFragmentContract.Model model;
    protected String viewConfigurationIdentifier;
    protected AdvancedMatrixCursor matrixCursor;
    protected Set<org.smartregister.configurableviews.model.View> visibleColumns = new TreeSet<>();
    protected RegisterConfiguration config;


    public ChildRegisterFragmentPresenter(ChildRegisterFragmentContract.View view,
                                          ChildRegisterFragmentContract.Model model,
                                          String viewConfigurationIdentifier) {
        this.viewReference = new WeakReference<>(view);
        this.model = model;
        this.viewConfigurationIdentifier = viewConfigurationIdentifier;
        this.config = model.defaultRegisterConfiguration();
    }

    @Override
    public void updateSortAndFilter(List<Field> filterList, Field sortField) {
        String filterText = model.getFilterText(filterList, getView().getString(R.string.filter));
        String sortText = model.getSortText(sortField);

        getView().updateFilterAndFilterStatus(filterText, sortText);

    }

    @Override
    public String getMainCondition() {
        return null;
    }

    @Override
    public String getDefaultSortQuery() {
        return null;
    }

    @Override
    public void processViewConfigurations() {
        if (StringUtils.isBlank(viewConfigurationIdentifier)) {
            return;
        }

        ViewConfiguration viewConfiguration = model.getViewConfiguration(viewConfigurationIdentifier);
        if (viewConfiguration != null) {
            config = (RegisterConfiguration) viewConfiguration.getMetadata();
            setVisibleColumns(model.getRegisterActiveColumns(viewConfigurationIdentifier));
        }

        if (config.getSearchBarText() != null && getView() != null) {
            getView().updateSearchBarHint(config.getSearchBarText());
        }

    }

    @Override
    public void initializeQueries(String mainCondition) {

        String tableName = Utils.metadata().getRegisterQueryProvider().getDemographicTable();

        String countSelect = model.countSelect(mainCondition);
        String mainSelect = model.mainSelect(mainCondition);

        getView().initializeQueryParams(tableName, countSelect, mainSelect);
        getView().initializeAdapter(visibleColumns);

        getView().countExecute();
        getView().filterandSortInInitializeQueries();

    }

    private void setVisibleColumns(Set<View> visibleColumns) {
        this.visibleColumns = visibleColumns;
    }

    protected ChildRegisterFragmentContract.View getView() {
        if (viewReference != null) return viewReference.get();
        else return null;
    }

    public AdvancedMatrixCursor getMatrixCursor() {
        return matrixCursor;
    }



    @Override
    public void startSync() {
//        empty block
    }

    @Override
    public void searchGlobally(String s) {
//        empty block
    }
}
