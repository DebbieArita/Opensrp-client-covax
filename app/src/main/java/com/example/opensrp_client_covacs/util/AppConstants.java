package com.example.opensrp_client_covacs.util;

public class AppConstants {

    public interface Locale {
        String Swahili = "sw";
    }

    public static final class KeyConstants {
        public static final String REACTION_VACCINE = "Reaction_Vaccine";
        public static final String CHILD = "child";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String ZEIR_ID = "zeir_id";
        public static final String GENDER = "gender";
        public static final String SYSTEM_OF_REGISTRATION = "system_of_registration";
        public static final String RELATIONAL_ID = "relational_id";
        public static final String ID_LOWER_CASE = "_id";
        public static final String BASE_ENTITY_ID = "base_entity_id";
        public static final String DOB = "dob";//Date Of Birth
        public static final String DATE_REMOVED = "date_removed";
        public static final String VIEW_CONFIGURATION_PREFIX = "ViewConfiguration_";
        public static final String HOME_FACILITY = "home_facility";
        public static final String TODAY = "today";
        public static final String BUTTON_TEXT = "buttonText";
        public static final String SEARCH_HINT = "searchHint";
        public static final String OPTIONS_TEXT = "options.text";
        public static final String SITE_CHARACTERISTICS = "site_characteristics";
        public static final String REGISTRATION_DATE = "registration_date";
        public static final String KEY = "key";
        public static final String ID = "id";
        public static final String CHILD_REGISTER_CARD_NUMBER = "child_register_card_number";
        public static final String PLACE_OF_BIRTH = "place_of_birth";
        public static final String OBJECT_ID = "object_id";
        public static final String CARD_STATUS = "card_status";
        public static final String CARD_STATUS_DATE = "card_status_date";
        public static final String REGISTRATION_LOCATION_ID = "registration_location_id";
        public static final String REGISTRATION_LOCATION_NAME = "registration_location_name";
        public static final String FIRST_HEALTH_FACILITY_CONTRACT = "first_health_facility_contract";
        public static final String RESIDENTIAL_ADDRESS = "residential_address";
        public static final String SMS_REMINDER = "sms_reminder";
        public static final String SMS_REMINDER_PHONE = "sms_reminder_phone";
        public static final String SMS_REMINDER_PHONE_FORMATTED = "sms_reminder_phone_formatted";
        public static final String RESIDENTIAL_ADDRESS_OTHER = "residential_address_other";
        public static final String RESIDENTIAL_AREA = "residential_area";
        public static final String OTHER = "other";
        public static final String OA_SERVICE_DATE = "OA_Service_Date";
        public static final String OPENSRP_ID = "opensrp_id";

    }

    public static class ConfigurationConstants {
        public static final String LOGIN = "login";
        public static final String CHILD_REGISTER = "child_register";

    }

    public static final class EventTypeConstants {
        public static final String CHILD_REGISTRATION = "Birth Registration";
        public static final String UPDATE_CHILD_REGISTRATION = "Update Birth Registration";
        public static final String OUT_OF_CATCHMENT = "Out of Area Service";
        public static final String CARD_STATUS_UPDATE = "card_status_update";
    }

    public interface JsonForm {
        String CHILD_ENROLLMENT = "child_enrollment";
        String OUT_OF_CATCHMENT_SERVICE = "out_of_catchment_service";
    }

    public static class RelationshipConstants {
        public static final String MOTHER = "mother";
    }

    public static class TableNameConstants {
        public static final String ALL_CLIENTS = "ec_client";
        public static final String REGISTER_TYPE = "client_register_type";
        public static final String CHILD_UPDATED_ALERTS = "child_updated_alerts";
        public static final String MOTHER_DETAILS = "ec_mother_details";
        public static final String CHILD_DETAILS = "ec_child_details";
        public static final String BOOSTER_VACCINES = "ec_booster_vaccines";
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
        public static final String MOTHER = "mother";
    }

    public interface IntentKeyUtil {
        String IS_REMOTE_LOGIN = "is_remote_login";
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

}
