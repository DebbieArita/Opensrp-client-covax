package com.example.opensrp_client_covacs.presenter;

import com.example.opensrp_client_covacs.contract.ChildRegisterFragmentContract;
import com.example.opensrp_client_covacs.fragment.ChildRegisterFragment;
import com.example.opensrp_client_covacs.model.ChildRegisterFragmentModel;

import org.smartregister.configurableviews.model.Field;
import org.smartregister.view.contract.BaseRegisterFragmentContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class ChildRegisterFragmentPresenter implements ChildRegisterFragmentContract.Presenter {

    protected WeakReference<ChildRegisterFragmentContract.View> viewReference;
    protected ChildRegisterFragmentContract.Model model;
    protected String viewConfigurationIdentifier;

    public ChildRegisterFragmentPresenter(ChildRegisterFragmentContract.View view, ChildRegisterFragmentContract.Model model,
                                      String viewConfigurationIdentifier) {
        this.viewReference = new WeakReference<>(view);
        this.model = model;
        this.viewConfigurationIdentifier = viewConfigurationIdentifier;
    }

    @Override
    public void updateSortAndFilter(List<Field> var1, Field var2) {

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

    }

    @Override
    public void initializeQueries(String s) {

    }

    @Override
    public void startSync() {

    }

    @Override
    public void searchGlobally(String s) {

    }
//    public ChildRegisterFragmentPresenter() {
//    }




//        public ChildRegisterFragmentPresenter(ChildRegisterFragmentContract.View view, ChildRegisterFragmentContract.Model model,
//                                          String viewConfigurationIdentifier) {
//        super();
//    }
//
//
//
//    @Override
//    public void processViewConfigurations() {
//
//    }
//
//    @Override
//    public void initializeQueries(String s) {
//
//    }
//
//    @Override
//    public void startSync() {
//
//    }
//
//    @Override
//    public void searchGlobally(String s) {
//
//    }
}
