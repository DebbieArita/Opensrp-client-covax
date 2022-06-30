package com.example.opensrp_client_covacs.provider;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.opensrp_client_covacs.holders.ChildRegisterViewHolder;

import org.smartregister.configurableviews.model.View;
import org.smartregister.cursoradapter.RecyclerViewProvider;
import org.smartregister.view.contract.SmartRegisterClient;
import org.smartregister.view.contract.SmartRegisterClients;
import org.smartregister.view.dialog.FilterOption;
import org.smartregister.view.dialog.ServiceModeOption;
import org.smartregister.view.dialog.SortOption;
import org.smartregister.view.viewholder.OnClickFormLauncher;

import java.util.Set;

public class ChildRegisterProvider implements RecyclerViewProvider<ChildRegisterViewHolder> {

    public final LayoutInflater inflater;

    private Set<View> visibleColumns;

    private android.view.View.OnClickListener onClickListener;
    private android.view.View.OnClickListener paginationClickListener;

    private Context context;

    public ChildRegisterProvider(LayoutInflater inflater, Set<View> visibleColumns, android.view.View.OnClickListener onClickListener, android.view.View.OnClickListener paginationClickListener, Context context) {
        this.inflater = inflater;
        this.visibleColumns = visibleColumns;
        this.onClickListener = onClickListener;
        this.paginationClickListener = paginationClickListener;
        this.context = context;
    }


    @Override
    public void getView(Cursor cursor, SmartRegisterClient smartRegisterClient, ChildRegisterViewHolder childRegisterViewHolder) {


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
        return null;
    }

    @Override
    public ChildRegisterViewHolder createViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder createFooterHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        return false;
    }
}
