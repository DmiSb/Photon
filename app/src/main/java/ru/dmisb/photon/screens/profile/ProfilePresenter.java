package ru.dmisb.photon.screens.profile;

import android.annotation.SuppressLint;
import android.view.MenuItem;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.data.dto.AlbumDto;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.dialogs.album.AlbumAddDialog;
import ru.dmisb.photon.ui.dialogs.album.AlbumEditDialog;
import ru.dmisb.photon.ui.dialogs.auth.AuthDialog;
import ru.dmisb.photon.ui.dialogs.confirm.ConfirmDialog;
import ru.dmisb.photon.ui.dialogs.register.RegisterDialog;
import ru.dmisb.photon.ui.helpers.BarBuilder;
import ru.dmisb.photon.ui.helpers.MenuItemHolder;

public class ProfilePresenter
        extends BasePresenter<ProfileView, ProfileModel>
        implements IProfilePresenter {

    private ProfileViewModel viewModel = new ProfileViewModel();
    private int position;

    ProfilePresenter(int position) {
        this.position = position;
    }

    //region ================= IProfilePresenter =================

    @Override
    public void onAuthClick() {
        if (getView() != null)
            AuthDialog.showDialog(getView().getContext())
                    .subscribe(
                            aBoolean -> {
                                if (aBoolean) {
                                    rootPresenter.showMessage(R.string.auth_success);
                                    initView();
                                    initActionBar();
                                }
                            }
                    );
    }

    @Override
    public void onRegisterClick() {
        if (getView() != null)
            RegisterDialog.showDialog(getView().getContext())
                    .subscribe(
                            aBoolean -> {
                                if (aBoolean) {
                                    rootPresenter.showMessage(R.string.register_success);
                                    initView();
                                    initActionBar();
                                }
                            },
                            throwable -> rootPresenter.showError(throwable)
                    );
    }

    @Override
    public void onAlbumAddClick() {
        if (getView() != null)
            AlbumAddDialog.showDialog(getView().getContext(), null)
                    .subscribe(
                            albumRes -> model.addAlbum(albumRes),
                            throwable -> rootPresenter.showError(throwable),
                            () -> getView().updateView()
                    );
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onAlbumDeleteClick(String albumId) {
        if (getView() != null) {
            ConfirmDialog.showDialog(getView().getContext(), R.string.profile_delete_album)
                    .subscribe(
                            aBoolean -> {
                                if (aBoolean) {
                                    model.deleteAlbum(albumId).subscribe(
                                            o -> getView().updateView(),
                                            throwable -> rootPresenter.showError((Throwable) throwable),
                                            () -> getView().updateView()
                                    );
                                }
                            }
                    );
        }
    }

    @Override
    public void onAlbumShowClick(String albumId, int position) {
        rootPresenter.showAlbumScreen(albumId, position);
    }

    @Override
    public void onAlbumEditClick(AlbumRealm album) {
        AlbumEditDialog.showDialog(getView().getContext(),
                new AlbumDto(album.getId(), album.getTitle(), album.getDescription())
        ).subscribe(
                albumRes -> {
                    model.updateAlbum(album, albumRes);
                    if (getView() != null)
                        getView().updateView();
                });
    }

    //endregion

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        ProfileScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        boolean userSigned = model.isSigned();
        viewModel.setSigned(userSigned);
        if (getView() != null) {
            getView().setViewModel(viewModel);

            if (userSigned)
                model.getUser().subscribe(
                        userRealm -> getView().setUserModel(userRealm),
                        throwable -> rootPresenter.showError(throwable)
                );

            getView().setListPosition(position);
        }
    }

    @SuppressLint("AlwaysShowAction")
    @Override
    protected void initActionBar() {
        if (rootPresenter != null) {
            BarBuilder barBuilder = rootPresenter.newBarBuilder()
                    .setToolbarVisible(true)
                    .setBottomBarVisible(true)
                    .setTitleResId(R.string.profile_action);

            boolean isUserSigned = viewModel.isSigned();

            if (isUserSigned)
                barBuilder.addAction(new MenuItemHolder(R.string.profile_add_album,
                        R.drawable.ic_add, null, false, MenuItem.SHOW_AS_ACTION_ALWAYS,
                        item -> {
                            onAlbumAddClick();
                            return false;
                        }
                ));

            barBuilder
                    .addAction(new MenuItemHolder(R.string.menu, R.drawable.ic_menu,
                        R.color.black, false, MenuItem.SHOW_AS_ACTION_ALWAYS,
                            item -> {
                                if (getView() != null) {
                                    if (isUserSigned)
                                        getView().showSignedPopupMenu(item);
                                    else
                                        getView().showUnsignedPopupMenu(item);
                                }
                                return false;
                            }
                    ))
                    .build();
        }
    }

    //endregion
}
