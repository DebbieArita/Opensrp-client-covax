package com.example.opensrp_client_covacs.presenter;

import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.application.CovacsApplication;
import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.interactor.ChildRegisterInteractor;
import com.example.opensrp_client_covacs.model.AppChildRegisterModel;

import org.apache.commons.lang3.tuple.Triple;
import org.smartregister.repository.EventClientRepository;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class AppChildRegisterPresenter implements ChildRegisterContract.Presenter, ChildRegisterContract.InteractorCallBack {

    protected WeakReference<ChildRegisterContract.View> viewReference;
    protected ChildRegisterContract.Model model;
    protected ChildRegisterContract.Interactor interactor;


    public AppChildRegisterPresenter(ChildRegisterActivity childRegisterActivity, AppChildRegisterModel model) {
        viewReference = new WeakReference<>(childRegisterActivity);
        interactor = new ChildRegisterInteractor();
        this.model = new AppChildRegisterModel();

    }

    private final EventClientRepository eventClientRepository = CovacsApplication.getInstance().eventClientRepository();

    @Override
    public void onUniqueIdFetched(Triple<String, Map<String, String>, String> var1, String var2) {

    }

    @Override
    public void onNoUniqueId() {

    }

    @Override
    public void onRegistrationSaved(boolean var1) {

        //
    }

    @Override
    public void saveLanguage(String var1) {

    }

    @Override
    public void startForm(String var1, String var2, String var3, String var4) throws Exception {

    }

    @Override
    public void startForm(String var1, String var2, Map<String, String> var3, String var4) throws Exception {

    }

//    @Override
//    public void saveForm(String var1, UpdateRegisterParams var2) {
//
//    }

//    @Override
//    public void saveForm(String var1, UpdateRegisterParams var2) {
//
//    }

    @Override
    public void saveOutOfCatchmentService(String var1, ChildRegisterContract.ProgressDialogCallback var2) {

    }

    @Override
    public void closeChildRecord(String var1) {

    }

    @Override
    public void registerViewConfigurations(List<String> viewIdentifiers) {
        model.registerViewConfigurations(viewIdentifiers);

    }

    @Override
    public void unregisterViewConfiguration(List<String> viewIdentifiers) {

    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    @Override
    public void updateInitials() {

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
