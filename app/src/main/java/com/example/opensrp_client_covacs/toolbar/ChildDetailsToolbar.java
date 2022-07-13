package com.example.opensrp_client_covacs.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.opensrp_client_covacs.R;

public class ChildDetailsToolbar extends BaseToolbar{
    public ChildDetailsToolbar(@NonNull Context context) {
        super(context);
    }

    public ChildDetailsToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildDetailsToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public int getSupportedMenu() {
        return R.menu.menu_child_detail_settings;
    }

    @Override
    public void prepareMenu() {

    }

    @Override
    public MenuItem onMenuItemSelected(MenuItem menuItem) {
        return null;
    }
}
