package com.example.opensrp_client_covacs.model;

import static com.example.opensrp_client_covacs.util.AppConstants.KeyConstants.COUNTY;
import static com.example.opensrp_client_covacs.util.AppConstants.KeyConstants.HEALTH_FACILITY_ID;

import androidx.annotation.NonNull;

import com.example.opensrp_client_covacs.application.CovacsApplication;

import org.smartregister.child.domain.ChildEventClient;
import org.smartregister.child.model.BaseChildRegisterModel;
import org.smartregister.clientandeventmodel.Client;
import org.smartregister.domain.tag.FormTag;
import org.smartregister.repository.AllSharedPreferences;

import java.util.List;

public class AppChildRegisterModel extends BaseChildRegisterModel {

    @Override
    public List<ChildEventClient> processRegistration(@NonNull String jsonString, FormTag formTag) {
        List<ChildEventClient> childEventClients = super.processRegistration(jsonString, formTag);
        //Add location name as part of child attributes to avoid fetching name from events
        for (ChildEventClient childEventClient : childEventClients) {
            Client client = childEventClient.getClient();
            AllSharedPreferences sharedPreferences = getSharedPrefs();
            String currentUser = sharedPreferences.fetchRegisteredANM();
            client.getAttributes().put(HEALTH_FACILITY_ID, sharedPreferences.fetchDefaultLocalityId(currentUser));
            client.getAttributes().put(COUNTY, sharedPreferences.fetchCurrentLocality());
        }
        return childEventClients;
    }

    private AllSharedPreferences getSharedPrefs() {
        return CovacsApplication.getInstance().context().allSharedPreferences();
    }
}
