package com.example.opensrp_client_covacs.util;

import org.smartregister.util.JsonFormUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChildJsonFormUtils extends JsonFormUtils {

    public static final String METADATA = "metadata";
    public static final String ENCOUNTER_TYPE = "encounter_type";
    public static final int REQUEST_CODE_GET_JSON = 2244;
    public static final String CURRENT_OPENSRP_ID = "current_opensrp_id";
    public static final String READ_ONLY = "read_only";
    public static final String STEP2 = "step2";
    public static final String RELATIONAL_ID = "relational_id";
    public static final String CURRENT_ZEIR_ID = "current_zeir_id";
    public static final String ZEIR_ID = "ZEIR_ID";
    public static final String updateBirthRegistrationDetailsEncounter = "Update Birth Registration";
    public static final String BCG_SCAR_EVENT = "Bcg Scar";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(com.vijay.jsonwizard.utils.FormUtils.NATIIVE_FORM_DATE_FORMAT_PATTERN, Locale.ENGLISH);
    public static final String GENDER = "gender";
    public static final String M_ZEIR_ID = "M_ZEIR_ID";
    public static final String F_ZEIR_ID = "F_ZEIR_ID";
    private static final String ENCOUNTER = "encounter";
    private static final String IDENTIFIERS = "identifiers";
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    private static final String OPENSRP_ID = "opensrp_id";
    private static final String FORM_SUBMISSION_FIELD = "formsubmissionField";
    private static final String LABEL_TEXT_STYLE = "label_text_style";
    private static final String RECURRING_SERVICES_FILE = "services.json";
}
