package com.example.opensrp_client_covacs.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseToolbar extends Toolbar {
    public BaseToolbar(@NonNull Context context) {
        super(context);
    }

    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract int getSupportedMenu();

    public abstract void prepareMenu();

    public abstract MenuItem onMenuItemSelected(MenuItem menuItem);
}
