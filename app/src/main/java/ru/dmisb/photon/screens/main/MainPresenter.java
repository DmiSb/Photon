package ru.dmisb.photon.screens.main;

import android.annotation.SuppressLint;
import android.view.MenuItem;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.dialogs.auth.AuthDialog;
import ru.dmisb.photon.ui.dialogs.register.RegisterDialog;
import ru.dmisb.photon.ui.helpers.ActionHolder;
import ru.dmisb.photon.ui.helpers.MenuItemHolder;

public class MainPresenter
        extends BasePresenter<MainView, MainModel>
        implements IMainPresenter {

    //region ================= IMainPresenter =================

    @Override
    public void onPhotoCardClick(String photoCardId) {
        rootPresenter.showPhotoCardScreen(photoCardId);
    }

    @Override
    public void onAuthClick() {
        if (getView() != null)
            AuthDialog.showDialog(getView().getContext())
                    .subscribe(
                            aBoolean -> {
                                if (aBoolean) {
                                    rootPresenter.showMessage(R.string.auth_success);
                                    initActionBar();
                                }

                            },
                            throwable -> rootPresenter.showError(throwable)
                    );
    }

    @Override
    public void onExitClick() {
        model.clearUserInfo();
        initActionBar();
    }

    @Override
    public void onRegisterClick() {
        if (getView() != null)
            RegisterDialog.showDialog(getView().getContext())
                    .subscribe(
                            aBoolean -> {
                                if (aBoolean) {
                                    rootPresenter.showMessage(R.string.register_success);
                                    initActionBar();
                                }
                            },
                            throwable -> rootPresenter.showError(throwable)
                    );
    }

    @Override
    public void onSearchClick() {
        rootPresenter.showSearchScreen();
    }

    @Override
    public void checkFilter() {
        if (model.isFilterActive())
            rootPresenter.showActionMessage(
                    new ActionHolder(
                            R.string.filter_enabled,
                            R.string.filter_disable,
                            v -> {
                                model.clearFilter();
                                initView();
                                initActionBar();
                            }
                    )
            );
    }

    //endregion

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        MainScreen.Component component = ScreenScoper.getComponent(ScreenScoper.MAIN_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        if (getView() != null) {
            getView().clearList();
            model.getPhotoCards()
                    .subscribe(
                            photoCardRealm -> {
                                getView().addPhotoCard(photoCardRealm);
                                rootPresenter.hideProgress();
                            }/*,
                            throwable -> rootPresenter.showError(throwable),
                            () -> rootPresenter.hideProgress()*/
                    );
        }
    }

    @SuppressLint("AlwaysShowAction")
    @Override
    protected void initActionBar() {
        if (rootPresenter != null) {
            rootPresenter.newBarBuilder()
                    .setToolbarVisible(true)
                    .setTitleResId(R.string.main_title)
                    .addAction(new MenuItemHolder(R.string.search, R.drawable.ic_search,
                            model.isFilterActive() ? R.color.colorAccent : null,
                            false,
                            MenuItem.SHOW_AS_ACTION_ALWAYS,
                            item -> {
                                onSearchClick();
                                return false;
                            }))
                    .addAction(new MenuItemHolder(R.string.menu, R.drawable.ic_gear,
                            R.color.black, false, MenuItem.SHOW_AS_ACTION_ALWAYS,
                            item -> {
                                if (getView() != null) {
                                    if (model.isSigned())
                                        getView().showSignedPopupMenu(item);
                                    else
                                        getView().showUnsignedPopupMenu(item);
                                }
                                return false;
                            }))
                    .setBottomBarVisible(true)
                    .build();
        }
    }

    //endregion
}