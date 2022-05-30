package com.example.opensrp_client_covacs.activity;

import android.content.Intent;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.fragment.ChildRegisterFragment;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.util.AppUtils;
import com.example.opensrp_client_covacs.view.NavDrawerActivity;
import com.google.android.material.internal.NavigationMenu;
import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.smartregister.child.activity.BaseChildRegisterActivity;
import org.smartregister.child.domain.UpdateRegisterParams;
import org.smartregister.child.util.ChildJsonFormUtils;
import org.smartregister.child.util.Constants;
import org.smartregister.child.util.Utils;
import org.smartregister.client.utils.domain.Form;
import org.smartregister.util.FormUtils;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.lang.ref.WeakReference;

import timber.log.Timber;

public class ChildRegisterActivity extends BaseChildRegisterActivity implements NavDrawerActivity {





    private NavigationMenu navigationMenu;
    private Fragment[] fragments;

    @Override
    protected Fragment[] getOtherFragments() {
        ADVANCED_SEARCH_POSITION = 1;
        fragments = new Fragment[1];
//        fragments[ADVANCED_SEARCH_POSITION - 1] = new WeakReference<>(new AdvancedSearchFragment()).get();
        return fragments;
    }

//    @Override
//    protected void onActivityResult() {
//        super.onActivityResult();
//    }



    @Override
    public String getRegistrationForm() {
        return AppConstants.JsonForm.CHILD_ENROLLMENT;
    }

    @Override
    protected void initializePresenter() {
    }

    @Override
    protected BaseRegisterFragment getRegisterFragment() {
        WeakReference<ChildRegisterFragment> weakReference = new WeakReference<>(new ChildRegisterFragment());
        return weakReference.get();
    }

//    @Override
//    protected void onResumption() {
//        super.onResumption();
//        WeakReference<ChildRegisterActivity> weakReference = new WeakReference<>(this);
//        navigationMenu = NavigationMenu.getInstance(weakReference.get());
//    }
//
//    @Override
//    public void openDrawer() {
//        if (navigationMenu != null) {
//            navigationMenu.openDrawer();
//        }
//    }

    @Override
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    public void startFormActivity(JSONObject jsonForm) {
        Form form = new Form();
        form.setWizard(false);
        form.setHideSaveLabel(true);
        form.setNextLabel("");

        Intent intent = new Intent(this, Utils.metadata().childFormActivity);
        String updatedForm = jsonForm.toString();
//        try {
//            updatedForm = FormUtils.obtainUpdatedForm(jsonForm, this);
//        } catch (JSONException e) {
//            Timber.e(e);
//        }
        intent.putExtra(Constants.INTENT_KEY.JSON, updatedForm);
        intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, form);
        intent.putExtra(JsonFormConstants.PERFORM_FORM_TRANSLATION, true);
        startActivityForResult(intent, ChildJsonFormUtils.REQUEST_CODE_GET_JSON);
    }

//    @Override
//    protected void updateSearchItems(String barcodeSearchTerm) {
//        advancedSearchFormData.put(AppConstants.KeyConstants.ZEIR_ID, barcodeSearchTerm);
//        Fragment fragment = fragments[ADVANCED_SEARCH_POSITION - 1];
//        if (fragment instanceof AdvancedSearchFragment) {
//            AdvancedSearchFragment advancedSearchFragment = (AdvancedSearchFragment) fragment;
//            advancedSearchFragment.searchByOpenSRPId(barcodeSearchTerm);
//        }
//    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void openDrawer() {

    }

    @Override
    protected void registerBottomNavigation() {
        super.registerBottomNavigation();

        MenuItem clients = bottomNavigationView.getMenu().findItem(R.id.action_clients);
        if (clients != null) {
            clients.setTitle(getString(R.string.header_children));
        }
        bottomNavigationView.getMenu().removeItem(R.id.action_library);
    }

//    @Override
//    public void saveForm(String jsonString, UpdateRegisterParams updateRegisterParam) {
//        String jsonForm = AppUtils.validateChildZone(jsonString);
//        super.saveForm(jsonForm, updateRegisterParam);
//    }
}
