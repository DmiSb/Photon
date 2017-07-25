package ru.dmisb.photon.ui.helpers;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.MenuItem;

public class MenuItemHolder {
    @Nullable
    private final @StringRes Integer titleResId;
    @Nullable
    private final @DrawableRes Integer iconResId;
    @Nullable
    private final @ColorRes Integer colorResId;
    private final boolean checked;
    private final int showAction;

    private MenuItem.OnMenuItemClickListener listener;

    public MenuItemHolder(@StringRes Integer titleResId,
                          @DrawableRes Integer iconResId,
                          @ColorRes Integer colorResId,
                          boolean checked,
                          int showAction,
                          MenuItem.OnMenuItemClickListener listener) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.colorResId = colorResId;
        this.checked = checked;
        this.showAction = showAction;
        this.listener = listener;
    }

    @Nullable
    public @StringRes Integer getTitleResId() {
        return titleResId;
    }

    @Nullable
    public @DrawableRes Integer getIconResId() {
        return iconResId;
    }

    @Nullable
    public @ColorRes Integer getColorResId() {
        return colorResId;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getShowAction() {
        return showAction;
    }

    public MenuItem.OnMenuItemClickListener getListener() {
        return listener;
    }

    public void clearItemListener() {
        listener = null;
    }
}