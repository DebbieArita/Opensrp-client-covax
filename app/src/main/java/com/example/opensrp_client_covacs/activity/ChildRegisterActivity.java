package com.example.opensrp_client_covacs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.fragment.ChildRegisterFragment;
import com.example.opensrp_client_covacs.model.AppChildRegisterModel;
import com.example.opensrp_client_covacs.presenter.AppChildRegisterPresenter;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.view.NavDrawerActivity;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.material.internal.NavigationMenu;
import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import org.smartregister.AllConstants;

import org.smartregister.client.utils.domain.Form;
import org.smartregister.view.activity.BaseRegisterActivity;
import org.smartregister.view.fragment.BaseRegisterFragment;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class ChildRegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_register);
    }
}

//public class ChildRegisterActivity extends BaseRegisterActivity implements NavDrawerActivity, RegistrationForm{
//
//    private NavigationMenu navigationMenu;
//    private Fragment[] fragments;
//
//    @Override
//    protected Fragment[] getOtherFragments() {
//        ADVANCED_SEARCH_POSITION = 1;
//        fragments = new Fragment[1];
////        fragments[ADVANCED_SEARCH_POSITION - 1] = new WeakReference<>(new AdvancedSearchFragment()).get();
//        return fragments;
//    }
//
//
////    what does this do
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode == AllConstants.BARCODE.BARCODE_REQUEST_CODE && resultCode == RESULT_OK) {
//            Barcode barcode = data.getParcelableExtra(AllConstants.BARCODE.BARCODE_KEY);
////            ((AppChildRegisterPresenter) presenter).updateChildCardStatus(barcode.displayValue);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onActivityResultExtended(int i, int i1, Intent intent) {
//        //to review
//    }
//
//    @Override
//    public List<String> getViewIdentifiers() {
//        return null;
//    }
//
//
//
//    @Override
//    public String getRegistrationForm() {
//        return AppConstants.JsonForm.CHILD_ENROLLMENT;
//    }
//
//    @Override
//    protected void initializePresenter() {
//        presenter = new AppChildRegisterPresenter();
//    }
//
//    @Override
//    protected BaseRegisterFragment getRegisterFragment() {
//        WeakReference<ChildRegisterFragment> weakReference = new WeakReference<>(new ChildRegisterFragment());
//        return weakReference.get();
//    }
//
//    @Override
//    protected void onResumption() {
//        super.onResumption();
//        WeakReference<ChildRegisterActivity> weakReference = new WeakReference<>(this);
////        navigationMenu = NavigationMenu.getInstance(weakReference.get());
//    }
//
//    @Override
//    public void startRegistration() {
//
//    }
//
//    @Override
//    public void startFormActivity(String s, String s1, Map<String, String> map) {
//
//    }
//
//    @Override
//    public void openDrawer() {
//        if (navigationMenu != null) {
////            navigationMenu.openDrawer();
//        }
//    }
//
//    @Override
//    public void onPause() {
//        EventBus.getDefault().unregister(this);
//        super.onPause();
//    }
//
//    @Override
//    public void startFormActivity(JSONObject jsonForm) {
//        Form form = new Form();
//        form.setWizard(false);
//        form.setHideSaveLabel(true);
//        form.setNextLabel("");
//
////        Intent intent = new Intent(this, Utils.metadata().childFormActivity);
////        String updatedForm = jsonForm.toString();
//////        try {
//////            updatedForm = FormUtils.obtainUpdatedForm(jsonForm, this);
//////        } catch (JSONException e) {
//////            Timber.e(e);
//////        }
////        intent.putExtra(Constants.INTENT_KEY.JSON, updatedForm);
////        intent.putExtra(JsonFormConstants.JSON_FORM_KEY.FORM, form);
////        intent.putExtra(JsonFormConstants.PERFORM_FORM_TRANSLATION, true);
////        startActivityForResult(intent, ChildJsonFormUtils.REQUEST_CODE_GET_JSON);
//    }
//
////    @Override
////    protected void updateSearchItems(String barcodeSearchTerm) {
////        advancedSearchFormData.put(AppConstants.KeyConstants.ZEIR_ID, barcodeSearchTerm);
////        Fragment fragment = fragments[ADVANCED_SEARCH_POSITION - 1];
////        if (fragment instanceof AdvancedSearchFragment) {
////            AdvancedSearchFragment advancedSearchFragment = (AdvancedSearchFragment) fragment;
////            advancedSearchFragment.searchByOpenSRPId(barcodeSearchTerm);
////        }
////    }
//
//    @Override
//    public void finishActivity() {
//        finish();
//    }
//
//
//    @Override
//    protected void registerBottomNavigation () {
//        super.registerBottomNavigation();
//
//        MenuItem clients = bottomNavigationView.getMenu().findItem(R.id.action_clients);
//        if (clients != null) {
//            clients.setTitle(getString(R.string.header_children));
//        }
//        bottomNavigationView.getMenu().removeItem(R.id.action_library);
//    }
//
////    @Override
////    public void saveForm(String jsonString, UpdateRegisterParams updateRegisterParam) {
////        String jsonForm = AppUtils.validateChildZone(jsonString);
////        super.saveForm(jsonForm, updateRegisterParam);
////    }
//
//}
