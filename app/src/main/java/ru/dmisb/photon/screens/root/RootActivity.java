package ru.dmisb.photon.screens.root;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;

import flow.Direction;
import flow.Flow;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseActivity;
import ru.dmisb.photon.core.IActivityResultView;
import ru.dmisb.photon.core.IBaseView;
import ru.dmisb.photon.databinding.ActivityRootBinding;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.flow.TreeKeyDispatcher;
import ru.dmisb.photon.screens.album.AlbumScreen;
import ru.dmisb.photon.screens.main.MainScreen;
import ru.dmisb.photon.screens.new_card.NewCardScreen;
import ru.dmisb.photon.screens.photo_card.PhotoCardScreen;
import ru.dmisb.photon.screens.profile.ProfileScreen;
import ru.dmisb.photon.screens.selector.SelectorScreen;
import ru.dmisb.photon.screens.upload.UploadScreen;
import ru.dmisb.photon.ui.helpers.ActionHolder;
import ru.dmisb.photon.ui.helpers.BottomNavigationHelper;
import ru.dmisb.photon.ui.helpers.MenuItemHolder;

public class RootActivity
        extends BaseActivity<ActivityRootBinding>
        implements IRootView {

    private ActionBar actionBar;
    private List<MenuItemHolder> actionBarMenuItems;

    @Inject
    IRootPresenter presenter;

    //region ================= Life cycle =================

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .defaultKey(new MainScreen())
                .dispatcher(new TreeKeyDispatcher(this))
                .install();
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_root);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        initComponent();
        initToolBar();
        initBottomBar();
    }

    private void initToolBar() {
        setSupportActionBar(viewDataBinding.toolbar);
        actionBar = getSupportActionBar();
    }

    private void initBottomBar() {
        viewDataBinding.bottomBar.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.main_action:
                            showMainScreen();
                            break;
                        case R.id.profile_action:
                            showProfileScreen(0);
                            break;
                        case R.id.add_photo_action:
                            showUploadScreen("");
                            break;
                    }
                    return true;
                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getCurrentScreen() != null && !getCurrentScreen().viewOnBackPressed() && !Flow.get(this).goBack()) {
            super.onBackPressed();
        }
    }

    private IBaseView getCurrentScreen() {
        return (IBaseView) viewDataBinding.rootFrame.getChildAt(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((RootPresenter) presenter).takeView(this);
    }

    @Override
    protected void onStop() {
        ((RootPresenter) presenter).dropView(this);
        super.onStop();
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        MortarScope rootActivityScope = MortarScope.findChild(getApplicationContext(), ScreenScoper.ROOT_SCOPE_NAME);
        return rootActivityScope.hasService(name) ? rootActivityScope.getService(name) : super.getSystemService(name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        View currView = viewDataBinding.rootFrame.getChildAt(0);
        if (currView != null && currView instanceof IActivityResultView && resultCode == RESULT_OK) {
            ((RootPresenter) presenter).takeView(this);
            ((IActivityResultView) currView).onActivityResult(requestCode, data);
        }
    }

    //endregion

    //region ================= IRootView =================

    @Override
    public void showActionMessage(ActionHolder action) {
        Snackbar snackbar = Snackbar.make(viewDataBinding.rootLayout,
                action.getMessageResId(), Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        snackbar.setAction(action.getActionTitleResId(), action.getListener());
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        snackbar.show();
    }

    @Override
    public void showMainScreen() {
        Flow.get(this).replaceHistory(new MainScreen(), Direction.REPLACE);
    }

    @Override
    public void showProfileScreen(int idx) {
        Flow.get(this).replaceHistory(new ProfileScreen(idx), Direction.REPLACE);
    }

    @Override
    public void showUploadScreen(String albumId) {
        Flow.get(this).replaceHistory(new UploadScreen(albumId), Direction.REPLACE);
    }

    @Override
    public void showPhotoCardScreen(String photoCardId) {
        Flow.get(this).set(new PhotoCardScreen(photoCardId));
    }

    @Override
    public void showSearchScreen() {
        Flow.get(this).replaceHistory(new SelectorScreen(), Direction.REPLACE);
    }

    @Override
    public void showAlbumScreen(String id, int idx) {
        Flow.get(this).replaceHistory(new AlbumScreen(id, idx), Direction.REPLACE);
    }

    @Override
    public void showNewCardScreen(String albumId, Uri photoUri) {
        Flow.get(this).replaceHistory(new NewCardScreen(albumId, photoUri), Direction.REPLACE);
    }

    //endregion

    //region ================= IBarView =================

    @Override
    public void setBarTitle(@StringRes Integer titleRes) {
        if (actionBar != null) {
            if (titleRes == null) {
                actionBar.setDisplayShowTitleEnabled(false);
            } else {
                actionBar.setDisplayShowTitleEnabled(true);
                String title = getResources().getString(titleRes);
                actionBar.setTitle(title);
            }
        }
    }

    @Override
    public void setBarTitle(String title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void setToolBarVisible(boolean visible) {
        viewDataBinding.toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setBackArrow(boolean enabled) {
        if (actionBar != null) {
            if (enabled) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
            } else {
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setHomeButtonEnabled(false);
            }
        }
    }

    @Override
    public void setOverFlowIcon(@DrawableRes Integer iconRes) {
        if (iconRes != null) {
            Drawable icon = ResourcesCompat.getDrawable(getResources(), iconRes, null);
            if (icon != null) {
                icon.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.black, null),
                        PorterDuff.Mode.SRC_ATOP);
            }
            viewDataBinding.toolbar.setOverflowIcon(icon);
        }
    }

    @Override
    public void setToolBarMenuItem(List<MenuItemHolder> items) {
        clearActionBarListeners();
        actionBarMenuItems = items;
        supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();

        if (actionBarMenuItems != null && actionBarMenuItems.size() > 0) {
            for (MenuItemHolder menuItemHolder : actionBarMenuItems) {
                MenuItem item = menu.add(menuItemHolder.getTitleResId());

                Drawable icon = null;
                if (menuItemHolder.getIconResId() != null)
                    icon = ResourcesCompat.getDrawable(getResources(), menuItemHolder.getIconResId(), null);
                if (icon != null) {
                    if (menuItemHolder.getColorResId() == null)
                        icon.setColorFilter(ResourcesCompat.getColor(getResources(), R.color.black, null),
                                PorterDuff.Mode.SRC_ATOP);
                    else
                        icon.setColorFilter(ResourcesCompat.getColor(getResources(), menuItemHolder.getColorResId(), null),
                                PorterDuff.Mode.SRC_ATOP);
                }

                item.setShowAsActionFlags(menuItemHolder.getShowAction())
                        .setIcon(icon)
                        .setCheckable(menuItemHolder.isChecked())
                        .setOnMenuItemClickListener(menuItemHolder.getListener());
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private void clearActionBarListeners() {
        if (viewDataBinding.toolbar.getMenu() != null) {
            for (int i = 0; i < viewDataBinding.toolbar.getMenu().size(); i++) {
                MenuItem item = viewDataBinding.toolbar.getMenu().getItem(i);
                item.setOnMenuItemClickListener(null);
            }
        }

        if (actionBarMenuItems != null && actionBarMenuItems.size() > 0) {
            for (MenuItemHolder itemHolder : actionBarMenuItems) {
                itemHolder.clearItemListener();
            }
            actionBarMenuItems.clear();
        }
    }

    @Override
    public void setTabLayout(ViewPager pager) {
        View view = viewDataBinding.appbarLayout.getChildAt(1);
        TabLayout tabView;
        if (view == null) {
            tabView = new TabLayout(this);
            tabView.setupWithViewPager(pager);
            tabView.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
            viewDataBinding.appbarLayout.addView(tabView);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabView));
        } else {
            tabView = (TabLayout) view;
            tabView.setupWithViewPager(pager);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabView));
        }
    }

    @Override
    public void removeTabLayout() {
        View tabView = viewDataBinding.appbarLayout.getChildAt(1);
        if (tabView != null && tabView instanceof TabLayout) {
            viewDataBinding.appbarLayout.removeView(tabView);
        }
    }

    @Override
    public void setBottomBarVisible(boolean visible) {
        viewDataBinding.bottomBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        BottomNavigationHelper.disableShiftMode(viewDataBinding.bottomBar);
    }

    //endregion

    //region ================= DI =================

    private void initComponent() {
        RootComponent rootComponent = ScreenScoper.getComponent(ScreenScoper.ROOT_SCOPE_NAME);
        if (rootComponent != null)
            rootComponent.inject(this);
    }

    //endregion
}