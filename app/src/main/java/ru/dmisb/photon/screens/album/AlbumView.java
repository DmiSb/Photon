package ru.dmisb.photon.screens.album;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.util.AttributeSet;
import android.view.MenuItem;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.databinding.ScreenAlbumBinding;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.helpers.PopupMenuHelper;

@SuppressWarnings("unused")
public class AlbumView extends BaseView<AlbumPresenter, ScreenAlbumBinding> {

    public AlbumView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void setViewModel(AlbumViewModel viewModel) {
        viewDataBinding.setModel(viewModel);
    }

    void showPopupMenu(MenuItem item){
        PopupMenu popup = PopupMenuHelper.createPopupMenu(this, R.menu.album_menu, item);
        if (popup != null) {
            popup.setOnMenuItemClickListener(item1 -> {
                switch (item1.getItemId()) {
                    case R.id.album_add_action:
                        presenter.onAddCardClick();
                        break;
                    case R.id.album_edit_action:
                        presenter.onAlbumEditClick();
                        break;
                    case R.id.album_delete_action:
                        break;
                }
                return false;
            });
            popup.show();
        }
    }

    //region ================= BaseView =================

    @Override
    public boolean viewOnBackPressed() {
        presenter.returnToProfile();
        return true;
    }

    @Override
    protected void initComponent() {
        AlbumScreen.Component component = ScreenScoper.getComponent(ScreenScoper.ALBUM_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void attachedToWindow() {

    }

    @Override
    protected void detachedFromWindow() {

    }

    //endregion
}
