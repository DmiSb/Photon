package ru.dmisb.photon.ui.helpers;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ru.dmisb.photon.core.IBarView;

public class BarBuilder {
    private IBarView view;

    private static int DEFAULT_MODE = 0;
    private static int TAB_MODE = 1;

    private boolean showBack;
    private boolean toolbarVisible;
    private boolean bottomBarVisible;
    @Nullable
    private @StringRes Integer titleRes;
    @Nullable
    private String title;
    private List<MenuItemHolder> items = new ArrayList<>();
    @Nullable
    private ViewPager pager;
    private int tabPageMode = DEFAULT_MODE;
    @Nullable
    private @DrawableRes Integer overFlowIcon;

    public BarBuilder(IBarView view) {
        this.view = view;
    }

    public BarBuilder setTitleResId(@StringRes Integer titleRes) {
        this.titleRes = titleRes;
        return this;
    }

    public BarBuilder setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    public BarBuilder setBackArrow(boolean enable) {
        this.showBack = enable;
        return this;
    }

    public BarBuilder setToolbarVisible(boolean toolbarVisible) {
        this.toolbarVisible = toolbarVisible;
        return this;
    }

    public BarBuilder setOverFlowIcon(@Nullable @DrawableRes Integer overFlowIcon) {
        this.overFlowIcon = overFlowIcon;
        return this;
    }

    public BarBuilder addAction(MenuItemHolder item) {
        this.items.add(item);
        return this;
    }

    public BarBuilder setTab(ViewPager pager) {
        this.pager = pager;
        this.tabPageMode = TAB_MODE;
        return this;
    }

    public BarBuilder setBottomBarVisible(boolean bottomBarVisible) {
        this.bottomBarVisible = bottomBarVisible;
        return this;
    }

    public void build() {
        if (view != null) {
            view.setToolBarVisible(toolbarVisible);

            if (titleRes != null)
                view.setBarTitle(titleRes);
            else
            if (title != null)
                view.setBarTitle(title);

            view.setBackArrow(showBack);

            if (overFlowIcon != null)
                view.setOverFlowIcon(overFlowIcon);

            view.setToolBarMenuItem(items);

            if (tabPageMode == TAB_MODE) {
                view.setTabLayout(pager);
            } else {
                view.removeTabLayout();
            }

            view.setBottomBarVisible(bottomBarVisible);
        }
    }
}
