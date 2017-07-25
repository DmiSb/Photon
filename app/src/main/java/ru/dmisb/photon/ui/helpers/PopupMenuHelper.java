package ru.dmisb.photon.ui.helpers;

import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;

public class PopupMenuHelper {

    @Nullable
    public static PopupMenu createPopupMenu(View view, @MenuRes int menuResId, MenuItem item) {
        View anchor = view.getRootView().findViewById(item.getItemId());
        if (anchor != null) {
            PopupMenu popup = new PopupMenu(view.getContext(), anchor);
            MenuInflater inflate = popup.getMenuInflater();
            inflate.inflate(menuResId, popup.getMenu());

            try {
                Field mFieldPopup = popup.getClass().getDeclaredField("mPopup");
                mFieldPopup.setAccessible(true);
                MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popup);
                mPopup.setForceShowIcon(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return popup;
        } else {
            return null;
        }
    }
}
