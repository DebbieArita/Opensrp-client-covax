package com.example.opensrp_client_covacs.domain;

import com.example.opensrp_client_covacs.activity.ChildImmunizationActivity;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.provider.ChildRegisterQueryProvider;
import com.vijay.jsonwizard.activities.JsonFormActivity;

import org.smartregister.view.activity.BaseProfileActivity;

import java.util.ArrayList;
import java.util.Set;

public class ChildMetadata {
    public final Class childFormActivity;
    public final Class childImmunizationActivity;
    private Class<? extends ChildRegisterActivity> childRegisterActivity;
    public final Class profileActivity;
    public final boolean formWizardValidateRequiredFieldsBefore;
    private ArrayList<String> locationLevels;
    private ArrayList<String> healthFacilityLevels;
    private Set<String> fieldsWithLocationHierarchy;
    public ChildRegister childRegister;
    private ChildRegisterQueryProvider registerQueryProvider;

    public ChildMetadata(Class<? extends JsonFormActivity> childFormActivity,
                         Class<? extends BaseProfileActivity> profileActivity,
                         Class<? extends ChildImmunizationActivity> childImmunizationActivity,
                         Class<? extends ChildRegisterActivity> childRegisterActivity,
                         boolean formWizardValidateRequiredFieldsBefore,
                         ChildRegisterQueryProvider registerQueryProvider) {
        this.childFormActivity = childFormActivity;
        this.childRegisterActivity = childRegisterActivity;
        this.childImmunizationActivity = childImmunizationActivity;
        this.profileActivity = profileActivity;
        this.formWizardValidateRequiredFieldsBefore = formWizardValidateRequiredFieldsBefore;
        setRegisterQueryProvider(new ChildRegisterQueryProvider());

    }

    public void updateChildRegister(String formName, String tableName, String registerEventType,
                                    String updateEventType, String config) {
        this.childRegister =
                new ChildRegister(formName, tableName, registerEventType, updateEventType, config);
    }

    public class ChildRegister {

        public final String formName;

        public final String tableName;

        public final String registerEventType;

        public final String updateEventType;

        public final String config;

        public ChildRegister(String formName, String tableName, String registerEventType,
                             String updateEventType, String config) {
            this.formName = formName;
            this.tableName = tableName;
            this.registerEventType = registerEventType;
            this.updateEventType = updateEventType;
            this.config = config;

        }
    }

    public class ChildActivityRegister {

        public final String tableName;
        public final int currentLimit;
        public final boolean showPagination;

        public ChildActivityRegister(String tableName, int currentLimit, boolean showPagination) {
            this.tableName = tableName;
            if (currentLimit <= 0) {
                this.currentLimit = 20;
            } else {
                this.currentLimit = currentLimit;
            }
            this.showPagination = showPagination;
        }
    }

    public ArrayList<String> getLocationLevels() {
        return locationLevels;
    }

    public void setLocationLevels(ArrayList<String> locationLevels) {
        this.locationLevels = locationLevels;
    }

    public ArrayList<String> getHealthFacilityLevels() {
        return healthFacilityLevels;
    }

    public void setHealthFacilityLevels(ArrayList<String> healthFacilityLevels) {
        this.healthFacilityLevels = healthFacilityLevels;
    }

    public Set<String> getFieldsWithLocationHierarchy() {
        return fieldsWithLocationHierarchy;
    }

    public void setFieldsWithLocationHierarchy(Set<String> fieldsWithLocationHierarchy) {
        this.fieldsWithLocationHierarchy = fieldsWithLocationHierarchy;
    }

    public ChildRegisterQueryProvider getRegisterQueryProvider() {
        return registerQueryProvider;
    }

    public void setRegisterQueryProvider(ChildRegisterQueryProvider registerQueryProvider) {
        this.registerQueryProvider = registerQueryProvider;
    }

    public Class<? extends ChildRegisterActivity> getChildRegisterActivity() {
        return childRegisterActivity;
    }
}


