package ru.dmisb.photon.screens.photo_card;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.data.storage.entities.TagRealm;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.databinding.ScreenPhotoCardBinding;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.custom_view.TagTextView;

public class PhotoCardView extends BaseView<PhotoCardPresenter, ScreenPhotoCardBinding> {

    private boolean tagBorderBlack = true;

    public PhotoCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //region ================= BaseView =================

    @Override
    protected void initComponent() {
        PhotoCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PHOTO_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void attachedToWindow() {

    }

    @Override
    protected void detachedFromWindow() {

    }

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    //endregion

    void setViewModel(PhotoCardRealm photoCard) {
        viewDataBinding.setModel(photoCard);

        for (TagRealm tagRealm : photoCard.getTags()) {
            addTag(tagRealm.getTag());
        }
    }

    void bindUser(UserRealm user) {
        viewDataBinding.setOwner(user);
    }

    private void addTag(String tag) {
        TagTextView view = new TagTextView(getContext());
        view.setTagText(tag);
        view.setSelected(tagBorderBlack);
        viewDataBinding.photoCardTagList.addView(view);
        tagBorderBlack = !tagBorderBlack;
    }
}