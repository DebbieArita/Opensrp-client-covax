package com.example.opensrp_client_covacs.fragment;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.contract.ChildRegisterFragmentContract;
import com.example.opensrp_client_covacs.cursor.AdvancedMatrixCursor;
import com.example.opensrp_client_covacs.domain.RepositoryHolder;
import com.example.opensrp_client_covacs.model.ChildRegisterFragmentModel;
import com.example.opensrp_client_covacs.presenter.ChildRegisterFragmentPresenter;
import com.example.opensrp_client_covacs.provider.ChildRegisterProvider;
import com.example.opensrp_client_covacs.util.Utils;
import com.example.opensrp_client_covacs.view.NavigationMenu;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.cursoradapter.RecyclerViewPaginatedAdapter;
import org.smartregister.cursoradapter.SmartRegisterQueryBuilder;
import org.smartregister.immunization.ImmunizationLibrary;
import org.smartregister.util.AppExecutors;
import org.smartregister.view.LocationPickerView;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.customcontrols.CustomFontTextView;
import org.smartregister.view.customcontrols.FontVariant;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import timber.log.Timber;

public class ChildRegisterFragment extends BaseRegisterFragment implements ChildRegisterFragmentContract.View, View.OnClickListener, LocationPickerView.OnLocationChangeListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View view = inflater.inflate(R.layout.activity_child_register, container, false);
        mView = view;
        onInitialization();
        setupViews(view);
        onResumption();

        return view;
    }




    @Override
    protected void initializePresenter() {

        if (getActivity() == null) {
            return;
        }
        String viewConfigurationIdentifier = ((BaseRegisterActivity) getActivity()).getViewIdentifiers().get(0);
        presenter = new ChildRegisterFragmentPresenter(this, new ChildRegisterFragmentModel(), viewConfigurationIdentifier); //to edit

    }

    @Override
    public void setupViews(View view) {
        super.setupViews(view);
        //todo setupViews

    }

    @Override
    public void setUniqueID(String uniqueID) {
        if (getSearchView() != null) {
            getSearchView().setText(uniqueID);
        }

    }

    @Override
    public void setAdvancedSearchFormData(HashMap<String, String> advancedSearchFormData) {

    }

    @Override
    protected String getMainCondition() {
        return presenter().getMainCondition();
    }

    @Override
    protected String getDefaultSortQuery() {
        return presenter().getDefaultSortQuery();
    }

    @Override
    protected void startRegistration() {
        ((ChildRegisterActivity) getActivity()).startRegistration();

    }

    @Override
    protected void onViewClicked(View view) {
        if (getActivity() == null) {
            return;
        }
        //TODO Register-Clickables...
    }

    @Override
    public void showNotFoundPopup(String opensrpId) {

    }

    @Override
    public void onClick(View view) {
        onViewClicked(view);
    }

    @Override
    public void initializeAdapter(Set<org.smartregister.configurableviews.model.View> visibleColumns) {
        RepositoryHolder repositoryHolder = new RepositoryHolder();
        repositoryHolder.setCommonRepository(commonRepository());
        repositoryHolder.setVaccineRepository(ImmunizationLibrary.getInstance().vaccineRepository());


        ChildRegisterProvider childRegisterProvider = new ChildRegisterProvider(requireActivity(), repositoryHolder,
                visibleColumns, registerActionHandler, paginationViewHandler);
        clientAdapter = new RecyclerViewPaginatedAdapter(null, childRegisterProvider, context().commonrepository(this.tablename));
        clientAdapter.setCurrentlimit(20);
        clientsView.setAdapter(clientAdapter);
    }

    @Override
    public void recalculatePagination(AdvancedMatrixCursor matrixCursor) {
        clientAdapter.setTotalcount(matrixCursor.getCount());
        Timber.v("Total count here%s", clientAdapter.getTotalcount());
        clientAdapter.setCurrentlimit(20);
        if (clientAdapter.getTotalcount() > 0) {
            clientAdapter.setCurrentlimit(clientAdapter.getTotalcount());
        }
        clientAdapter.setCurrentoffset(0);
    }

    @Override
    public ChildRegisterFragmentContract.Presenter presenter() {
        return (ChildRegisterFragmentContract.Presenter) presenter;

    }

    //TODO make use of AppExecutors...
    @Override
    public void countExecute() {
        String sql = Utils.metadata().getRegisterQueryProvider().getCountExecuteQuery(mainCondition, filters);
        Timber.i(sql);
        int totalCount = commonRepository().countSearchIds(sql);

        clientAdapter.setTotalcount(totalCount);
        Timber.i("Total Register Count %d", clientAdapter.getTotalcount());
        clientAdapter.setCurrentlimit(20);
        clientAdapter.setCurrentoffset(0);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final AdvancedMatrixCursor matrixCursor = ((ChildRegisterFragmentPresenter) presenter).getMatrixCursor();
        if (!globalQrSearch || matrixCursor == null) {
            if (id == LOADER_ID) {
                return new CursorLoader(getActivity()) {
                    @Override
                    public Cursor loadInBackground() {
                        String query = filterAndSortQuery();
                        return commonRepository().rawCustomQueryForAdapter(query);
                    }
                };
            } else {
                return new CursorLoader(getContext());
            }
        } else {
            globalQrSearch = false;
            if (id == LOADER_ID) {// Returns a new CursorLoader
                return new CursorLoader(getActivity()) {
                    @Override
                    public Cursor loadInBackground() {
                        return matrixCursor;
                    }
                };
            }// An invalid id was passed in
            return new CursorLoader(getContext());
        }
    }

    protected String filterAndSortQuery() {
        SmartRegisterQueryBuilder sqb = new SmartRegisterQueryBuilder(mainSelect);
        String query = "";
        try {
            if (isValidFilterForFts(commonRepository())) {
                String sql = Utils.metadata().getRegisterQueryProvider().getObjectIdsQuery(mainCondition, filters) + (StringUtils.isBlank(getDefaultSortQuery()) ? "" : " order by " + getDefaultSortQuery());

                sql = sqb.addlimitandOffset(sql, clientAdapter.getCurrentlimit(), clientAdapter.getCurrentoffset());

                List<String> ids = commonRepository().findSearchIds(sql);
                query = Utils.metadata().getRegisterQueryProvider().mainRegisterQuery() +
                        " WHERE _id IN (%s)" + (StringUtils.isBlank(getDefaultSortQuery()) ? "" : " order by " + getDefaultSortQuery());

                String joinedIds = "'" + StringUtils.join(ids, "','") + "'";
                return query.replace("%s", joinedIds);
            } else {
                if (!TextUtils.isEmpty(filters) && !TextUtils.isEmpty(Sortqueries)) {
                    sqb.addCondition(filters);
                    query = sqb.orderbyCondition(Sortqueries);
                    query = sqb.Endquery(sqb.addlimitandOffset(query
                            , clientAdapter.getCurrentlimit()
                            , clientAdapter.getCurrentoffset()));
                }
                return query;
            }
        } catch (Exception e) {
            Timber.e(e);
        }

        return query;
    }


    @Override
    public void onLocationChange(String s) {

    }

    protected int getToolBarTitle() {
        return 0;
    }
}
