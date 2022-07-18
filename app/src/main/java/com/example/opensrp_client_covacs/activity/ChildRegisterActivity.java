package com.example.opensrp_client_covacs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.application.CovacsApplication;
import com.example.opensrp_client_covacs.contract.ChildRegisterContract;
import com.example.opensrp_client_covacs.fragment.ChildRegisterFragment;
import com.example.opensrp_client_covacs.listener.ChildBottomNavigationListener;
import com.example.opensrp_client_covacs.model.AppChildRegisterModel;
import com.example.opensrp_client_covacs.presenter.AppChildRegisterPresenter;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.util.ChildJsonFormUtils;
import com.example.opensrp_client_covacs.util.Utils;
import com.example.opensrp_client_covacs.view.NavDrawerActivity;
import com.example.opensrp_client_covacs.view.NavigationMenu;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationBarView;
import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.AllConstants;
import org.smartregister.client.utils.domain.Form;
import org.smartregister.helper.BottomNavigationHelper;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class ChildRegisterActivity extends BaseRegisterActivity implements ChildRegisterContract.View, NavDrawerActivity {

    private int disabledMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_child_register); //to test
        NavigationMenu.getInstance(this);
    }

    @Override
    protected void registerBottomNavigation() {

        bottomNavigationHelper = new BottomNavigationHelper();
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        if(bottomNavigationView != null) {
            bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_AUTO);
            bottomNavigationView.getMenu().removeItem(R.id.action_clients);
            bottomNavigationView.getMenu().removeItem(R.id.action_register);
            bottomNavigationView.getMenu().removeItem(R.id.action_search);
            bottomNavigationView.getMenu().removeItem(R.id.action_library);

            bottomNavigationView.inflateMenu(R.menu.bottom_nav_child_menu);

            bottomNavigationHelper.disableShiftMode(bottomNavigationView);

        }

        ChildBottomNavigationListener childBottomNavigationListener = getChildBottomNavigationListener();
        bottomNavigationView.setOnNavigationItemSelectedListener(childBottomNavigationListener);
    }

    protected ChildBottomNavigationListener getChildBottomNavigationListener() {
        return new ChildBottomNavigationListener(this);
    }

    @Override
    public void onBackPressed() {
        if (currentPage == 0) {
            super.onBackPressed();
        } else {
            switchToBaseFragment();
            setSelectedBottomBarMenuItem(R.id.action_home);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onChildRegisterResumption();
    }

    protected void onChildRegisterResumption() {

        reEnableMenuItem();


        setSelectedBottomBarMenuItem(R.id.action_home);
    }

    private void reEnableMenuItem() {
        if (disabledMenuId != 0)
            bottomNavigationView.getMenu().findItem(disabledMenuId).setEnabled(true);
    }


    @Override
    protected void initializePresenter() {
        presenter = new AppChildRegisterPresenter(this, new AppChildRegisterModel()); //to edit
    }


    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        WeakReference<ChildRegisterFragment> weakReference = new WeakReference<>(new ChildRegisterFragment());
        return weakReference.get();
    }

    @Override
    protected Fragment[] getOtherFragments() {
        return new Fragment[0];
    }


    @Override
    public void startFormActivity(String formName, String entityId, Map<String, String> metaData) {

    }

    String getFormTitle() {
        return null;
    }

    @Override
    public void startFormActivity(JSONObject jsonForm) {
        Intent intent = new Intent(this, Utils.metadata().childFormActivity);
        intent.putExtra(AppConstants.INTENT_KEY.JSON, jsonForm.toString());


        Form form = new Form();
        form.setWizard(false);
        form.setHideSaveLabel(true);
        form.setNextLabel("");
        form.setName(getFormTitle());
        form.setActionBarBackground(R.color.actionbar);
        form.setNavigationBackground(R.color.toolbar_background);
        form.setHomeAsUpIndicator(R.drawable.ic_action_clear);


        intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, form);
        intent.putExtra(JsonFormConstants.PERFORM_FORM_TRANSLATION, true);
        startActivityForResult(intent, ChildJsonFormUtils.REQUEST_CODE_GET_JSON);
    }

    @Override
    protected void onActivityResultExtended(int i, int i1, Intent intent) {

    }

    @Override
    protected void onResumption() {
        reEnableMenuItem();
        setSelectedBottomBarMenuItem(R.id.action_home);

    }

    @Override
    public List<String> getViewIdentifiers() {
        return Arrays.asList(CovacsApplication.getInstance().getMetadata().childRegister.config);
    }

    @Override
    public ChildRegisterContract.Presenter presenter() {
        return (ChildRegisterContract.Presenter) presenter;
    }


    @Override
    public void setActiveMenuItem(int menuItemId) {
        disabledMenuId = menuItemId;

    }

    @Override
    public void onRegistrationSaved() {
        Intent intent = new Intent(this, ChildRegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);

    }


    @Override
    public void startRegistration() {
        startFormActivity(getFormJson(getRegistrationForm()));
    }


    public JSONObject getFormJson(String formIdentity) {
        try {
            InputStream inputStream = getApplicationContext().getAssets()
                    .open("json.form/" + formIdentity + ".json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                    "UTF-8"));
            String jsonString;
            StringBuilder stringBuilder = new StringBuilder();
            while ((jsonString = reader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }
            inputStream.close();

            return new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getRegistrationForm() {
        return AppConstants.JsonForm.CHILD_ENROLLMENT;
    }



    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void openDrawer() {

    }

}
