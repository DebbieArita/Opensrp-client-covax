package com.example.opensrp_client_covacs.util;


import com.example.opensrp_client_covacs.BuildConfig;
import com.example.opensrp_client_covacs.activity.ChildFormActivity;
import com.example.opensrp_client_covacs.activity.ChildImmunizationActivity;
import com.example.opensrp_client_covacs.activity.ChildProfileActivity;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.domain.ChildMetadata;
import com.example.opensrp_client_covacs.provider.ChildRegisterQueryProvider;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FormUtils{
    public static ChildMetadata getMetadata(ChildProfileActivity childProfileActivity, String defaultLocation, ArrayList<String> locationHierarchy) {
        ChildMetadata metadata = new ChildMetadata(ChildFormActivity.class,
                childProfileActivity.getClass(), ChildImmunizationActivity.class,
                ChildRegisterActivity.class, false, new ChildRegisterQueryProvider());
        metadata.updateChildRegister(AppConstants.JsonForm.CHILD_ENROLLMENT, AppConstants.RegisterTable.CLIENT,
                AppConstants.EventType.CHILD_REGISTRATION, AppConstants.EventType.UPDATE_CHILD_REGISTRATION,
                AppConstants.configuration.CHILD_REGISTER);

        metadata.setFieldsWithLocationHierarchy(new HashSet<>(Arrays.asList("Home_Facility", "Birth_Facility_Name")));
        metadata.setLocationLevels(new ArrayList<>(Arrays.asList(BuildConfig.LOCATION_LEVELS)));
        metadata.setHealthFacilityLevels(new ArrayList<>(Arrays.asList(BuildConfig.HEALTH_FACILITY_LEVELS)));

        //TODO Check if I really need this... looks redundant
//        metadata.updateChildDueRegister(AppConstants.TABLE_NAME.CHILD, Integer.MAX_VALUE, false);
//        metadata.setDefaultLocation(defaultLocation);
//        metadata.setLocationHierarchy(locationHierarchy);
//        metadata.setLocationFields(locationFields);
        return metadata;
    }


}
