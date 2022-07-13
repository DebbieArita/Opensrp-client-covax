package com.example.opensrp_client_covacs.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.application.CovacsApplication;
import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.domain.UpdateRegisterParams;
import com.example.opensrp_client_covacs.interactor.ChildRegisterInteractor;
import com.example.opensrp_client_covacs.model.AppChildRegisterModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.json.JSONObject;
import org.smartregister.configurableviews.ConfigurableViewsLibrary;
import org.smartregister.repository.EventClientRepository;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class AppChildRegisterPresenter implements ChildRegisterContract.Presenter, ChildRegisterContract.InteractorCallBack {

    protected WeakReference<ChildRegisterContract.View> viewWeakReference;
    protected ChildRegisterContract.Model model;
    protected ChildRegisterContract.Interactor interactor;


    public AppChildRegisterPresenter(ChildRegisterActivity childRegisterActivity, AppChildRegisterModel model) {
        viewWeakReference = new WeakReference<>(childRegisterActivity);
        interactor = new ChildRegisterInteractor();
        this.model = model;

    }

    private final EventClientRepository eventClientRepository = CovacsApplication.getInstance().eventClientRepository();

    @Override
    public void onUniqueIdFetched(Triple<String, Map<String, String>, String> var1, String var2) {

    }

    @Override
    public void onNoUniqueId() {

    }

    @Override
    public void onRegistrationSaved() {
        if (getView() != null) {
            getView().hideProgressDialog();
            getView().onRegistrationSaved();
        }
    }

    public ChildRegisterContract.View getView() {
        if (viewWeakReference != null) {
            return viewWeakReference.get();
        } else {
            return null;
        }
    }

//    @Override
//    public void saveLanguage(String var1) {
//
//    }

    @Override
    public void startForm(String formName, String entityId) throws Exception {
        JSONObject formJsonObject = model.getFormAsJson(getView().getContext(), formName, entityId);
        getView().startFormActivity(formJsonObject);
    }

    @Override
    public void startEditForm(String formName, String updateEventType, String entityId, Map<String, String> valueMap) throws Exception {
        JSONObject formJsonObject = model.getEditFormAsJson(getView().getContext(), formName, updateEventType, entityId, valueMap);
        getView().startFormActivity(formJsonObject);
    }

    @Override
    public void saveForm(String jsonString, String table) {
        try {
            if (getView() != null)
                getView().showProgressDialog(R.string.saving_dialog_title);

            interactor.saveRegistration(jsonString, table, this);
        } catch (Exception ex) {
            Timber.e(ex);
        }

    }


//    @Override
//    public void closeChildRecord(String var1) {
//
//    }

    @Override
    public void registerViewConfigurations(List<String> viewIdentifiers) {
        if (viewIdentifiers != null)
            ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().registerViewConfigurations(viewIdentifiers);

    }

    @Override
    public void unregisterViewConfiguration(List<String> viewIdentifiers) {
        if (viewIdentifiers != null)
            ConfigurableViewsLibrary.getInstance().getConfigurableViewsHelper().unregisterViewConfiguration(viewIdentifiers);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {
        viewWeakReference = null;
        interactor.onDestroy(isChangingConfiguration);
        // Activity destroyed set interactor to null
        if (!isChangingConfiguration) {
            interactor = null;
            model = null;
        }
    }

    @Override
    public void updateInitials() {
        String initials = model.getInitials();
        if (initials != null) {
            getView().updateInitialsText(initials);
        }
    }


//    private final EventClientRepository eventClientRepository = CovacsApplication.getInstance().eventClientRepository();
//
//    public AppChildRegisterPresenter(ChildRegisterContract.View view, ChildRegisterContract.Model model) {
//        super(view, model);
//    }
//
//    @Override
//    public void onRegistrationSaved(boolean isEdit) { super.onRegistrationSaved(isEdit);}

//    public void updateCStatus
}
