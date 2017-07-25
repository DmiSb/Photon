package ru.dmisb.photon.core;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;

import java.util.List;

import ru.dmisb.photon.ui.helpers.MenuItemHolder;


public interface IBarView {
    void setBarTitle(@StringRes Integer titleRes);
    void setBarTitle(String title);
    void setToolBarVisible(boolean visible);
    void setBackArrow(boolean enabled);
    void setOverFlowIcon(@DrawableRes Integer iconRes);
    void setToolBarMenuItem(List<MenuItemHolder> items);

    void setTabLayout(ViewPager pager);
    void removeTabLayout();

    void setBottomBarVisible(boolean visible);
}
