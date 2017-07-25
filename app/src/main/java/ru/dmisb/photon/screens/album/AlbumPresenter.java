package ru.dmisb.photon.screens.album;

import android.annotation.SuppressLint;
import android.view.MenuItem;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.data.dto.AlbumDto;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.dialogs.album.AlbumEditDialog;
import ru.dmisb.photon.ui.helpers.MenuItemHolder;

public class AlbumPresenter
        extends BasePresenter<AlbumView, AlbumModel>
        implements IAlbumPresenter {

    private String albumId;
    private int position;
    private AlbumViewModel viewModel = new AlbumViewModel();

    AlbumPresenter(String albumId, int position) {
        this.albumId = albumId;
        this.position = position;
    }

    //region ================= IAlbumPresenter =================

    @Override
    public void returnToProfile() {
        rootPresenter.showProfileScreen(position);
    }

    @Override
    public void onAlbumEditClick() {
        AlbumEditDialog.showDialog(getView().getContext(),
                    new AlbumDto(viewModel.getId(), viewModel.getTitle(), viewModel.getDescription())
                )
                .subscribe(
                        albumRes -> {
                            model.updateAlbum(
                                new AlbumDto(albumRes.getId(), albumRes.getTitle(), albumRes.getDescription())
                            );
                            viewModel.setTitle(albumRes.getTitle());
                            viewModel.setDescription(albumRes.getDescription());
                        },
                        throwable -> rootPresenter.showError(throwable)
                );
    }

    @Override
    public void onAddCardClick() {
        rootPresenter.showUploadScreen(albumId);
    }

    //endregion

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        AlbumScreen.Component component = ScreenScoper.getComponent(ScreenScoper.ALBUM_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        if (getView() != null) {
            AlbumRealm album = model.getAlbum(albumId);
            viewModel.setId(albumId);
            viewModel.setTitle(album.getTitle());
            viewModel.setDescription(album.getDescription());
            viewModel.setCardsCount(String.valueOf(album.getPhotocards().size()));
            getView().setViewModel(viewModel);
        }
    }

    @SuppressLint("AlwaysShowAction")
    @Override
    protected void initActionBar() {
        rootPresenter.newBarBuilder()
                .setToolbarVisible(true)
                .setBackArrow(true)
                .addAction(new MenuItemHolder(R.string.album_menu, R.drawable.ic_menu,
                         R.color.black, false, MenuItem.SHOW_AS_ACTION_ALWAYS,
                         item -> {
                             if (getView() != null)
                                 getView().showPopupMenu(item);
                             return false;
                         }
                ))
                .setBottomBarVisible(true)
                .build();
    }

    //endregion
}
