package com.example.opensrp_client_covacs.util;

import org.smartregister.immunization.ImmunizationLibrary;
import org.smartregister.immunization.db.VaccineRepo;

import java.util.List;

public class DBQueryHelper {
    public static String getSortQuery() {
        return Utils.metadata().getRegisterQueryProvider().getDemographicTable() + "." + AppConstants.KeyConstants.LAST_INTERACTED_WITH + " DESC ";
    }

    public static String getFilterSelectionCondition(boolean urgentOnly) {
//
//        final String AND = " AND ";
//        final String OR = " OR ";
//        final String IS_NULL_OR = " IS NULL OR ";
//        final String TRUE = "'true'";
//
//        String mainCondition = " (" + AppConstants.CHILD_STATUS.INACTIVE + IS_NULL_OR + AppConstants.CHILD_STATUS.INACTIVE + " != " + TRUE + " ) " +
//                AND + " (" + AppConstants.CHILD_STATUS.LOST_TO_FOLLOW_UP + IS_NULL_OR + AppConstants.CHILD_STATUS.LOST_TO_FOLLOW_UP + " != " + TRUE + " ) " +
//                AND + " ( ";
//
////        List<VaccineRepo.Vaccine> vaccines = ImmunizationLibrary.getVaccineCacheMap().get(Constants.CHILD_TYPE).vaccineRepo;
//    }
        return null;
    }
}
