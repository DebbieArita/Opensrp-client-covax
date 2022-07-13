package com.example.opensrp_client_covacs.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.opensrp_client_covacs.fragment.ChildFormFragment;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.util.ChildJsonFormUtils;
import com.vijay.jsonwizard.activities.JsonFormActivity;
import com.example.opensrp_client_covacs.R;
import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.clientandeventmodel.DateUtil;
import org.smartregister.util.LangUtils;

import java.text.ParseException;

import java.util.Map;

import timber.log.Timber;

public class ChildFormActivity extends JsonFormActivity {
    private ChildFormFragment childFormFragment;
    private boolean enableOnCloseDialog = true;
    private JSONObject form;

    @Override
    protected void attachBaseContext(android.content.Context base) {
        String language = LangUtils.getLanguage(base);
        Configuration newConfiguration = LangUtils.setAppLocale(base, language);

        super.attachBaseContext(base);

        applyOverrideConfiguration(newConfiguration);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            form = new JSONObject(currentJsonState());
        } catch (JSONException e) {
            Timber.e(e.toString());
        }

        enableOnCloseDialog = getIntent().getBooleanExtra(AppConstants.FormActivity.EnableOnCloseDialog, true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {

            String et = form.getString(ChildJsonFormUtils.ENCOUNTER_TYPE);

            confirmCloseTitle = getString(R.string.confirm_form_close);
            confirmCloseMessage = et.trim().toLowerCase().contains("update") ? this.getString(R.string.any_changes_you_make) : this.getString(R.string.confirm_form_close_explanation);

            setConfirmCloseTitle(confirmCloseTitle);
            setConfirmCloseMessage(confirmCloseMessage);


        } catch (JSONException e) {
            Timber.e(e.toString());
        }
    }

    @Override
    public void initializeFormFragment() {
        initializeFormFragmentCore();
    }

    protected void initializeFormFragmentCore() {
        childFormFragment = ChildFormFragment.getFormFragment(JsonFormConstants.FIRST_STEP_NAME);
        getSupportFragmentManager().beginTransaction().add(com.vijay.jsonwizard.R.id.container, childFormFragment).commit();
    }


    @Override
    public void writeValue(String stepName, String key, String value, String openMrsEntityParent, String openMrsEntity, String openMrsEntityId, boolean popup) throws JSONException {
        super.writeValue(stepName, key, value, openMrsEntityParent, openMrsEntity, openMrsEntityId, popup);
    }

    @Override
    public void onBackPressed() {
        if (enableOnCloseDialog) {
            AlertDialog dialog = new AlertDialog.Builder(this, R.style.AppThemeAlertDialog).setTitle(confirmCloseTitle)
                    .setMessage(confirmCloseMessage).setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ChildFormActivity.this.finish();
                        }
                    }).setPositiveButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Timber.d("No button on dialog in %s", JsonFormActivity.class.getCanonicalName());
                        }
                    }).create();

            dialog.show();

        } else {
            ChildFormActivity.this.finish();
        }
    }

    protected static String getMainConditionString(Map<String, String> entityMap) {
        String mainConditionString = "";

        return mainConditionString;
    }

    public static boolean isDate(String dobString) {
        try {
            DateUtil.yyyyMMdd.parse(dobString);
            return true;
        } catch (ParseException | NullPointerException e) {
            return false;
        }
    }
}
