package com.example.opensrp_client_covacs.util;

import android.util.Pair;

import com.example.opensrp_client_covacs.activity.ChildFormActivity;
import com.example.opensrp_client_covacs.activity.ChildImmunizationActivity;
import com.example.opensrp_client_covacs.activity.ChildProfileActivity;
import com.example.opensrp_client_covacs.activity.ChildRegisterActivity;
import com.example.opensrp_client_covacs.domain.ChildMetadata;
import com.example.opensrp_client_covacs.provider.ChildRegisterQueryProvider;

import org.smartregister.view.activity.BaseProfileActivity;

import java.util.ArrayList;

public class FormUtils {
    public static ChildMetadata getMetadata(ChildProfileActivity childProfileActivity, String defaultLocation, ArrayList<String> locationHierarchy) {
        ChildMetadata metadata = new ChildMetadata(ChildFormActivity.class,
                childProfileActivity.getClass(), ChildImmunizationActivity.class,
                ChildRegisterActivity.class, false, new ChildRegisterQueryProvider());
        metadata.updateChildRegister(AppConstants.JsonForm.CHILD_ENROLLMENT, AppConstants.TableNameConstants.ALL_CLIENTS,
                AppConstants.EventTypeConstants.CHILD_REGISTRATION, AppConstants.EventType.UPDATE_CHILD_REGISTRATION,
                AppConstants.configuration.CHILD_REGISTER);

//        metadata.updateChildDueRegister(CoreConstants.TABLE_NAME.CHILD, Integer.MAX_VALUE, false);
//        metadata.updateFamilyActivityRegister(CoreConstants.TABLE_NAME.CHILD_ACTIVITY, Integer.MAX_VALUE, false);
//        metadata.updateFamilyOtherMemberRegister(CoreConstants.TABLE_NAME.FAMILY_MEMBER, Integer.MAX_VALUE, false);
//        metadata.setDefaultLocation(defaultLocation);
//        metadata.setLocationHierarchy(locationHierarchy);
//        metadata.setLocationFields(locationFields);
        return metadata;
    }
}
