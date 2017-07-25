package ru.dmisb.photon.screens.profile;

import android.content.Context;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MenuItem;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.databinding.ScreenProfileBinding;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.helpers.PopupMenuHelper;

@SuppressWarnings("unused")
public class ProfileView extends BaseView<ProfilePresenter, ScreenProfileBinding> {

    private ProfileAdapter adapter = new ProfileAdapter();

    //region ================= ProfileView =================

    void setViewModel(ProfileViewModel viewModel) {
        viewDataBinding.setModel(viewModel);
    }

    void setUserModel(UserRealm user) {
        viewDataBinding.setUser(user);
        adapter.setUser(user);
    }

    void setListPosition(int idx) {
        viewDataBinding.profileAlbumList.scrollToPosition(idx);
    }

    void updateView() {
        adapter.notifyDataSetChanged();
    }

    public ProfileView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void showSignedPopupMenu(MenuItem item) {
        showPopupMenu(R.menu.profile_menu_signed, item);
    }

    void showUnsignedPopupMenu(MenuItem item) {
        showPopupMenu(R.menu.profile_menu_unsigned, item);
    }

    private void showPopupMenu(@MenuRes int menuResId, MenuItem item) {
        PopupMenu popup = PopupMenuHelper.createPopupMenu(this, menuResId, item);
        if (popup != null) {
            popup.setOnMenuItemClickListener(item1 -> {
                switch (item1.getItemId()) {
                    case R.id.profile_login_action:
                        presenter.onAuthClick();
                        break;
                    case R.id.profile_register_action:
                        presenter.onRegisterClick();    
                        break;
                    case R.id.profile_edit_action:
                        // TODO: 23.07.2017 create profile_edit_action 
                        break;
                    case R.id.profile_add_album_action:
                        presenter.onAlbumAddClick();
                        break;
                }
                return false;
            });
            popup.show();
        }
    }

    //endregion

    //region ================= BaseView =================

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    @Override
    protected void initComponent() {
        ProfileScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initRecyclerView();
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        RecyclerView recyclerView = viewDataBinding.profileAlbumList;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void attachedToWindow() {
        viewDataBinding.profileAuthLayout.profileAuth.setOnClickListener(v -> presenter.onAuthClick());
        viewDataBinding.profileAuthLayout.profileRegister.setOnClickListener(v -> presenter.onRegisterClick());
    }

    @Override
    protected void detachedFromWindow() {
        viewDataBinding.profileAuthLayout.profileAuth.setOnClickListener(null);
        viewDataBinding.profileAuthLayout.profileRegister.setOnClickListener(null);
    }

    //endregion
}
