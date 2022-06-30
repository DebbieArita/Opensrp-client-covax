package com.example.opensrp_client_covacs.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.contract.ChildRegisterFragmentContract;
import com.example.opensrp_client_covacs.model.ChildRegisterFragmentModel;
import com.example.opensrp_client_covacs.presenter.ChildRegisterFragmentPresenter;

import org.smartregister.view.LocationPickerView;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;
import java.util.Set;

public class ChildRegisterFragment extends BaseRegisterFragment implements ChildRegisterFragmentContract.View, View.OnClickListener, LocationPickerView.OnLocationChangeListener {

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        return super.onCreateView(inflater, container, savedInstanceState);
//
////        getActivity().
//
//
//        View view =inflater.inflate(R.layout.child_reg_frag_activity, container, false);
//        this.mView = view;
//        this.onInitialization();
//        this.setupViews(view);
//        this.onResumption();
//
//        return view;
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//
//        View view = inflater.inflate(R.layout.smart_register_activity_customized, container, false);
//        mView = view;
//        onInitialization();
//        setupViews(view);
//        onResumption();
//
//        return view;
//    }


    @Override
    protected void initializePresenter() {

        if (getActivity() == null) {
            return;
        }

        String viewConfigurationIdentifier = ((BaseRegisterActivity) getActivity()).getViewIdentifiers().get(0);
        presenter = new ChildRegisterFragmentPresenter(this, new ChildRegisterFragmentModel(), viewConfigurationIdentifier); //to edit

    }

    @Override
    public void setUniqueID(String qrCode) {

    }

    @Override
    public void setAdvancedSearchFormData(HashMap<String, String> advancedSearchFormData) {

    }

    @Override
    protected String getMainCondition() {
        return null;
    }

    @Override
    protected String getDefaultSortQuery() {
        return null;
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

    }

    @Override
    public void showNotFoundPopup(String opensrpId) {

    }

    @Override
    public void onClick(View view) {
        onViewClicked(view);
    }

    @Override
    public void initializeAdapter(Set<org.smartregister.configurableviews.model.View> var1) {

    }

    @Override
    public ChildRegisterFragmentContract.Presenter presenter() {
        return (ChildRegisterFragmentContract.Presenter) presenter;

    }

    @Override
    public void onLocationChange(String s) {

    }
}
