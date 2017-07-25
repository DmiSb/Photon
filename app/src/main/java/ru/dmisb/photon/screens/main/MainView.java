package ru.dmisb.photon.screens.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.databinding.ScreenMainBinding;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.helpers.PopupMenuHelper;
import ru.dmisb.photon.utils.AppConfig;

public class MainView
        extends BaseView<MainPresenter, ScreenMainBinding> {

    private MainAdapter adapter = new MainAdapter();

    public MainView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()){
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        RecyclerView recyclerView = viewDataBinding.mainPhotoCardList;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addPhotoCard(PhotoCardRealm photoCard) {
        adapter.addPhotoCard(photoCard);
    }

    public void clearList() {
        adapter.clearList();
    }

    void showSignedPopupMenu(MenuItem item) {
        showPopupMenu(R.menu.main_menu_signed, item);
    }

    void showUnsignedPopupMenu(MenuItem item) {
        showPopupMenu(R.menu.main_menu_unsigned, item);
    }

    private void showPopupMenu(@MenuRes int menuResId, MenuItem item) {
        PopupMenu popup = PopupMenuHelper.createPopupMenu(this, menuResId, item);
        if (popup != null) {
            popup.setOnMenuItemClickListener(item1 -> {
                switch (item1.getItemId()) {
                    case R.id.main_login_action:
                        presenter.onAuthClick();
                        break;
                    case R.id.main_register_action:
                        presenter.onRegisterClick();
                        break;
                    case R.id.main_logout_action:
                        presenter.onExitClick();
                        break;
                    case R.id.main_send_to_support:
                        sendToSupport();
                        break;
                }
                return false;
            });
            popup.show();
        }
    }

    public void sendToSupport() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        intent.setData(Uri.parse("mailto:" + AppConfig.SUPPORT_EMAIL));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.send_subject));
        getContext().startActivity(
                Intent.createChooser(intent, getContext().getString(R.string.send_to_support))
        );
    }

    //region ================= BaseView =================

    @Override
    protected void initComponent() {
        MainScreen.Component component = ScreenScoper.getComponent(ScreenScoper.MAIN_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void attachedToWindow() {
        presenter.checkFilter();
    }

    @Override
    protected void detachedFromWindow() {

    }

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    //endregion
}