package com.example.opensrp_client_covacs.fragment;

import android.view.View;

import org.smartregister.view.fragment.BaseRegisterFragment;

import java.util.HashMap;

public class ChildRegisterFragment extends BaseRegisterFragment {
    @Override
    protected void initializePresenter() {

    }

    @Override
    public void setUniqueID(String qrCode) {

    }

    @Override
    public void setAdvancedSearchFormData(HashMap<String, String> advancedSearchFormData) {

    }

    @Override
    protected String getMainCondition() {
        return null;
    }

    @Override
    protected String getDefaultSortQuery() {
        return null;
    }

    @Override
    protected void startRegistration() {

    }

    @Override
    protected void onViewClicked(View view) {

    }

    @Override
    public void showNotFoundPopup(String opensrpId) {

    }


//    @Override
//    protected String getMainCondition() {
//        return null;
//    }
//
//    @Override
//    protected String getDefaultSortQuery() {
//        return null;
//    }
//
//    @Override
//    protected String filterSelectionCondition(boolean b) {
//        return null;
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }

//    @Override
//    protected void initializePresenter() {
//        if (getActivity() == null) {
//            return;
//        }
//
//        String viewConfigurationIdentifier = ((BaseRegisterActivity) getActivity()).getViewIdentifiers().get(0);
//        presenter = new ChildRegisterFragmentPresenter(this, new ChildRegisterFragmentModel(), viewConfigurationIdentifier);
//    }
//
//    @Override
//    protected String getMainCondition() {
//        return presenter().getMainCondition();
//    }
//
//    @Override
//    protected String getDefaultSortQuery() {
//        return presenter().getDefaultSortQuery();
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    protected void onViewClicked(View view) {
//        super.onViewClicked(view);
//        RegisterClickables registerClickables = new RegisterClickables();
//        if (view.getTag(R.id.record_action) != null) {
//            registerClickables.setRecordWeight(
//                    Constants.RECORD_ACTION.GROWTH.equals(view.getTag(R.id.record_action)));
//            registerClickables.setRecordAll(
//                    Constants.RECORD_ACTION.VACCINATION.equals(view.getTag(R.id.record_action)));
//            registerClickables.setNextAppointmentDate(view.getTag(R.id.next_appointment_date) != null ? String
//                    .valueOf(view.getTag(R.id.next_appointment_date)) : "");
//        }
//
//        CommonPersonObjectClient client = null;
//        if (view.getTag() != null && view.getTag() instanceof CommonPersonObjectClient) {
//            client = (CommonPersonObjectClient) view.getTag();
//        }
//
//        switch (view.getId()) {
//            case R.id.child_profile_info_layout:
//                ChildImmunizationActivity.launchActivity(getActivity(), client, registerClickables);
//                break;
//            case R.id.record_growth:
//                registerClickables.setRecordWeight(true);
//                ChildImmunizationActivity.launchActivity(getActivity(), client, registerClickables);
//                break;
//            case R.id.record_vaccination:
//                registerClickables.setRecordAll(true);
//                ChildImmunizationActivity.launchActivity(getActivity(), client, registerClickables);
//                break;
//            case R.id.filter_selection:
//                toggleFilterSelection();
//                break;
//            case R.id.global_search:
//                ((ChildRegisterActivity) getActivity()).startAdvancedSearch();
//                break;
//            case R.id.scan_qr_code:
//                ((ChildRegisterActivity) getActivity()).startQrCodeScanner();
//                break;
//            case R.id.back_button:
//                ((ChildRegisterActivity) getActivity()).openDrawer();
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    @Override
//    protected String filterSelectionCondition(boolean urgentOnly) {
//        return DBQueryHelper.getFilterSelectionCondition(urgentOnly);
//    }
//
//    @Override
//    public void setupViews(View view) {
//        super.setupViews(view);
//        View globalSearchButton = view.findViewById(org.smartregister.child.R.id.global_search);
//        View registerClientButton = view.findViewById(org.smartregister.child.R.id.register_client);
//        if (globalSearchButton != null && registerClientButton != null) {
//            globalSearchButton.setVisibility(View.INVISIBLE);
//            registerClientButton.setVisibility(View.INVISIBLE);
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        onViewClicked(view);
//    }
//
//    @Override
//    public void setupSearchView(View view) {
//        super.setupSearchView(view);
//        ((View) searchView.getParent().getParent()).setBackgroundColor(
//                ContextCompat.getColor(requireContext(), R.color.toolbar_background));
//        searchView.setHint(requireContext().getString(R.string.search_hint));
//    }
}
