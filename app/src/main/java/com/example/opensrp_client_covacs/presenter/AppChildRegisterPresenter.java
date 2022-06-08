package com.example.opensrp_client_covacs.presenter;

import com.example.opensrp_client_covacs.application.CovacsApplication;

import org.smartregister.child.contract.ChildRegisterContract;
import org.smartregister.child.presenter.BaseChildRegisterPresenter;
import org.smartregister.repository.EventClientRepository;

public class AppChildRegisterPresenter extends BaseChildRegisterPresenter {
    private final EventClientRepository eventClientRepository = CovacsApplication.getInstance().eventClientRepository();

    public AppChildRegisterPresenter(ChildRegisterContract.View view, ChildRegisterContract.Model model) {
        super(view, model);
    }

    @Override
    public void onRegistrationSaved(boolean isEdit) { super.onRegistrationSaved(isEdit);}

//    public void updateCStatus
}
