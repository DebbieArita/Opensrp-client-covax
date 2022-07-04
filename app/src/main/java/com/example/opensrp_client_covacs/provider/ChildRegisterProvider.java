package com.example.opensrp_client_covacs.provider;

import static com.example.opensrp_client_covacs.util.AppConstants.KeyConstants.FEMALE;
import static com.example.opensrp_client_covacs.util.AppConstants.KeyConstants.MALE;
import static com.example.opensrp_client_covacs.util.Utils.getDuration;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opensrp_client_covacs.R;
import com.example.opensrp_client_covacs.domain.RepositoryHolder;
import com.example.opensrp_client_covacs.holders.ChildRegisterViewHolder;
import com.example.opensrp_client_covacs.util.AppConstants;
import com.example.opensrp_client_covacs.util.Utils;

import org.apache.commons.lang3.text.WordUtils;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.commonregistry.CommonRepository;
import org.smartregister.configurableviews.model.View;
import org.smartregister.cursoradapter.RecyclerViewFragment;
import org.smartregister.cursoradapter.RecyclerViewProvider;
import org.smartregister.immunization.repository.VaccineRepository;
import org.smartregister.view.contract.SmartRegisterClient;
import org.smartregister.view.contract.SmartRegisterClients;
import org.smartregister.view.dialog.FilterOption;
import org.smartregister.view.dialog.ServiceModeOption;
import org.smartregister.view.dialog.SortOption;
import org.smartregister.view.fragment.BaseRegisterFragment;
import org.smartregister.view.viewholder.OnClickFormLauncher;

import java.util.Set;

public class ChildRegisterProvider implements RecyclerViewProvider<ChildRegisterViewHolder> {

    public final LayoutInflater inflater;
    private Set<View> visibleColumns;
    private android.view.View.OnClickListener onClickListener;
    private android.view.View.OnClickListener paginationClickListener;
    private Context context;
    private CommonRepository commonRepository;
    private VaccineRepository vaccineRepository;


    public ChildRegisterProvider(Context context, RepositoryHolder repositoryHolder, Set<View> visibleColumns, android.view.View.OnClickListener onClickListener, android.view.View.OnClickListener paginationClickListener) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.visibleColumns = visibleColumns;
        this.onClickListener = onClickListener;
        this.paginationClickListener = paginationClickListener;
        this.context = context;

        this.commonRepository = repositoryHolder.getCommonRepository();
        this.vaccineRepository = repositoryHolder.getVaccineRepository();
    }



    @Override
    public void getView(Cursor cursor, SmartRegisterClient client, ChildRegisterViewHolder viewHolder) {
        CommonPersonObjectClient pc = (CommonPersonObjectClient) client;
        if (visibleColumns != null && visibleColumns.isEmpty()) {
            populatePatientColumn(pc, client, viewHolder);
        }

    }

    @Override
    public void getFooterView(RecyclerView.ViewHolder viewHolder, int i, int i1, boolean b, boolean b1) {

    }

    @Override
    public SmartRegisterClients updateClients(FilterOption filterOption, ServiceModeOption serviceModeOption, FilterOption filterOption1, SortOption sortOption) {
        return null;
    }

    @Override
    public void onServiceModeSelected(ServiceModeOption serviceModeOption) {

    }

    @Override
    public OnClickFormLauncher newFormLauncher(String s, String s1, String s2) {
        return null;
    }

    @Override
    public LayoutInflater inflater() {
        return inflater;
    }

    @Override
    public ChildRegisterViewHolder createViewHolder(ViewGroup parent) {
        android.view.View view = inflater.inflate(R.layout.adapter_child_register_list_row, parent, false);
        return new ChildRegisterViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder createFooterHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    protected void populatePatientColumn(CommonPersonObjectClient pc, SmartRegisterClient client, ChildRegisterViewHolder viewHolder) {
        String firstName = Utils.getValue(pc.getColumnmaps(), AppConstants.KeyConstants.FIRST_NAME, true);
        String lastName = Utils.getValue(pc.getColumnmaps(), AppConstants.KeyConstants.LAST_NAME, true);
        String childName = org.smartregister.util.Utils.getName(firstName, lastName);

        fillValue(viewHolder.textViewChildName, WordUtils.capitalize(childName));

        String dobString = getDuration(Utils.getValue(pc.getColumnmaps(), AppConstants.KeyConstants.DOB, false));
        fillValue(viewHolder.textViewChildAge, dobString);


        setAddressAndGender(pc, viewHolder);
//
//        addStatusButtonClickListener(client, viewHolder);

    }

    public void setAddressAndGender(CommonPersonObjectClient pc, ChildRegisterViewHolder viewHolder) {
        String address = Utils.getValue(pc.getColumnmaps(), AppConstants.KeyConstants.COUNTY, true);
        String genderKey = Utils.getValue(pc.getColumnmaps(), AppConstants.KeyConstants.GENDER, true);
        String gender = "";
        if (genderKey.equalsIgnoreCase(MALE)) {
            gender = context.getString(R.string.male);
        } else if (genderKey.equalsIgnoreCase(FEMALE)) {
            gender = context.getString(R.string.female);
        }
        StringBuilder addressGender = new StringBuilder();
        addressGender.append(address).append(" \u00B7 ").append(gender);
        fillValue(viewHolder.textViewAddressGender, addressGender.toString());
    }


    protected static void fillValue(TextView v, String value) {
        if (v != null) {
            v.setText(value);
        }
    }

}
