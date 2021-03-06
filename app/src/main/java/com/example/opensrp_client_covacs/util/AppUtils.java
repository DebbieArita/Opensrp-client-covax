package com.example.opensrp_client_covacs.util;


import android.content.ContentValues;

import androidx.annotation.NonNull;

import com.example.opensrp_client_covacs.BuildConfig;
import com.example.opensrp_client_covacs.application.CovacsApplication;

import org.apache.commons.lang3.StringUtils;
import org.smartregister.commonregistry.AllCommonsRepository;
import org.smartregister.domain.Client;
import org.smartregister.location.helper.LocationHelper;
import org.smartregister.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class AppUtils extends Utils {
    public static final ArrayList<String> ALLOWED_LEVELS;
    public static final String COUNTY = "County";
    public static final String DEFAULT_LOCATION_LEVEL = "Health Facility";

    static {
        ALLOWED_LEVELS = new ArrayList<>();
        ALLOWED_LEVELS.add(DEFAULT_LOCATION_LEVEL);
        ALLOWED_LEVELS.add(COUNTY);
    }


    private static void updateChildTables(Client client, ContentValues values, String tableName) {
        AllCommonsRepository allCommonsRepository = CovacsApplication.getInstance().getContext().allCommonsRepositoryobjects(tableName);
        if (allCommonsRepository != null) {
            allCommonsRepository.update(tableName, values, client.getBaseEntityId());
            allCommonsRepository.updateSearch(client.getBaseEntityId());
        }
    }

    @NonNull
    public static ArrayList<String> getLocationLevels() {
        return new ArrayList<>(Arrays.asList(BuildConfig.LOCATION_LEVELS));
    }

    @NonNull
    public static ArrayList<String> getHealthFacilityLevels() {
        return new ArrayList<>(Arrays.asList(BuildConfig.HEALTH_FACILITY_LEVELS));
    }

    @NonNull
    public static String getCurrentLocality() {
        String selectedLocation = CovacsApplication.getInstance().getContext().allSharedPreferences().fetchCurrentLocality();
        if (StringUtils.isBlank(selectedLocation)) {
            selectedLocation = LocationHelper.getInstance().getDefaultLocation();
            CovacsApplication.getInstance().getContext().allSharedPreferences().saveCurrentLocality(selectedLocation);
        }
        return selectedLocation;
    }


    public static boolean timeBetweenLastExecutionAndNow(int i, String reportJobExecutionTime) {
        try {
            long executionTime = Long.parseLong(reportJobExecutionTime);
            long now = System.currentTimeMillis();
            long diffNowExecutionTime = now - executionTime;
            return TimeUnit.MILLISECONDS.toMinutes(diffNowExecutionTime) > i;
        } catch (NumberFormatException e) {
            Timber.e(e);
            return false;
        }
    }

    public static void updateSyncStatus(boolean isComplete) {
        CovacsApplication.getInstance().getContext().allSharedPreferences().savePreference("syncComplete", String.valueOf(isComplete));
    }


}
