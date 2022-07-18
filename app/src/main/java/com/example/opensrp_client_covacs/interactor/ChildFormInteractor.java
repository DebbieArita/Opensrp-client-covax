package com.example.opensrp_client_covacs.interactor;

import com.example.opensrp_client_covacs.widgets.ChildCheckboxTextFactory;
import com.example.opensrp_client_covacs.widgets.ChildDatePickerFactory;
import com.example.opensrp_client_covacs.widgets.ChildEditTextFactory;
import com.example.opensrp_client_covacs.widgets.ChildSpinnerFactory;
import com.vijay.jsonwizard.constants.JsonFormConstants;
import com.vijay.jsonwizard.interactors.JsonFormInteractor;

public class ChildFormInteractor extends JsonFormInteractor {
    private static final ChildFormInteractor CHILD_INTERACTOR_INSTANCE = new ChildFormInteractor();

    protected ChildFormInteractor() {
        super();
    }

//    public static JsonFormInteractor getInstance(){
//        return CHILD_INTERACTOR_INSTANCE;
//    }

    public static JsonFormInteractor getChildInteractorInstance() {
        return CHILD_INTERACTOR_INSTANCE;
    }

    @Override
    protected void registerWidgets() {
        super.registerWidgets();
        map.put(JsonFormConstants.EDIT_TEXT, new ChildEditTextFactory());
        map.put(JsonFormConstants.DATE_PICKER, new ChildDatePickerFactory());
        map.put(JsonFormConstants.CHECK_BOX, new ChildCheckboxTextFactory());
        map.put(JsonFormConstants.SPINNER, new ChildSpinnerFactory());
    }

}
