package com.example.opensrp_client_covacs.util;

public class AppConstants {

    public interface Locale {
        String Swahili = "sw";
    }

    public static final String SQLITE_DATE_TIME_FORMAT = "yyyy-MM-dd";


    public static final class KeyConstants {
        public static final String REACTION_VACCINE = "Reaction_Vaccine";
        public static final String PARENT_LOCATION = "parent_location";
        public static final String LOCATION = "location";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String COUNTRY = "country";
        public static final String REGION = "region";
        public static final String REGION_ID = "region_id";
        public static final String COUNTY = "county";
        public static final String COUNTY_ID = "county_id";
        public static final String HEALTH_FACILITY = "health_facility";
        public static final String HEALTH_FACILITY_ID = "health_facility_id";
        public static final String HOME_FACILITY = "home_facility";
        public static final String ZEIR_ID = "zeir_id";
        public static final String GENDER = "gender";
        public static final String DOB = "dob";//Date Of Birth
        public static final String CHILD = "child";
        public static final String AGE = "age";
        public static final String WEIGHT = "weight";
        public static final String COVAX_PROTECTION = "covacs_protection";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String RESIDENTIAL_AREA = "residential_area";
        public static final String RESIDENTIAL_AREA_OTHER = "residential_area_other";
        public static final String RESIDENTIAL_ADDRESS = "residential_address";
        public static final String TODAY = "today";
        public static final String BUTTON_TEXT = "buttonText";
        public static final String SEARCH_HINT = "searchHint";
        public static final String OPTIONS_TEXT = "options.text";
        public static final String REGISTRATION_DATE = "registration_date";
        public static final String KEY = "key";
        public static final String ID = "id";
        public static final String OTHER = "other";
        public static final String VIEW_CONFIGURATION_PREFIX = "ViewConfiguration_";
        public static final String PFIZER = "pfizer";
        public static final String PFIZER_2 = "pfizer_2";
        public static final String PFIZER_BOOSTER = "pfizer_booster";
        public static final String PFIZER_BOOSTER_2 = "pfizer_booster_2";
        public static final String JOHNSON_AND_JANSEN = "johnson_and_jansen";
        public static final String MODERNA = "moderna";
        public static final String MODERNA_2 = "moderna_2";
        public static final String MODERNA_BOOSTER = "moderna_booster";
        public static final String MODERNA_BOOSTER_2 = "moderna_booster_2";

    }

    public static class ConfigurationConstants {
        public static final String LOGIN = "login";
        public static final String CHILD_REGISTER = "child_register";

    }

    public static final class EventTypeConstants {
        public static final String CHILD_REGISTRATION = "Subject Registration";
        public static final String UPDATE_CHILD_REGISTRATION = "Update Subject Registration";

    }

    public static final class EventType {
        public static final String UPDATE_CHILD_REGISTRATION = "Update Subject Registration";

    }

    public interface JsonForm {
        String CHILD_ENROLLMENT = "subject_registration";
    }

    public interface RegisterTable {
        String CLIENT = "ec_client_fields";
        String VACCINE_DETAILS = "ec_client_vaccine";
    }

    public static class TableNameConstants {
        public static final String ALL_CLIENTS = "ec_client";
        public static final String REGISTER_TYPE = "client_register_type";
        public static final String CHILD_UPDATED_ALERTS = "child_updated_alerts";
        public static final String CHILD_DETAILS = "ec_child_details";
//        public static final String BOOSTER_VACCINES = "ec_booster_vaccines";
    }

    public interface Columns {
        interface RegisterType {
            String BASE_ENTITY_ID = "base_entity_id";
            String REGISTER_TYPE = "register_type";
            String DATE_REMOVED = "date_removed";
            String DATE_CREATED = "date_created";
        }
    }

    public static final class EntityTypeConstants {
        public static final String CHILD = "child";
    }

    public interface IntentKeyUtil {
        String IS_REMOTE_LOGIN = "is_remote_login";
    }

    public static final class INTENT_KEY {
        public static final String JSON = "json";
        public static final String BASE_ENTITY_ID = "base_entity_id";
        public static final String EXTRA_CHILD_DETAILS = "child_details";
        public static final String EXTRA_REGISTER_CLICKABLES = "register_clickables";
        public static final String LOCATION_ID = "location_id";
        public static final String PROVIDER_ID = "provider_id";
        public static final String NEXT_APPOINTMENT_DATE = "next_appointment_date";
    }

    public interface RegisterType {
        String CHILD = "child";
    }

    public interface Pref {
        String APP_VERSION_CODE = "APP_VERSION_CODE";
        String INDICATOR_DATA_INITIALISED = "INDICATOR_DATA_INITIALISED";
    }

    public interface File {
        String INDICATOR_CONFIG_FILE = "configs/reporting/indicator-definitions.yml";
    }

    public static class FormActivity {
        public static final String EnableOnCloseDialog = "EnableOnCloseDialog";
    }

    public static class configuration {
        public static final String LOGIN = "login";
        public static final String CHILD_REGISTER = "family_register";
        public static final String CHILD_MEMBER_REGISTER = "child_member_register";
    }
}
