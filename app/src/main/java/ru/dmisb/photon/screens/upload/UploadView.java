package ru.dmisb.photon.screens.upload;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import ru.dmisb.photon.core.BaseView;
import ru.dmisb.photon.core.IActivityResultView;
import ru.dmisb.photon.databinding.ScreenUploadBinding;
import ru.dmisb.photon.flow.ScreenScoper;

@SuppressWarnings("unused")
public class UploadView
        extends BaseView<UploadPresenter, ScreenUploadBinding> implements IActivityResultView {

    public UploadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //region ================= IActivityResultView =================

    @Override
    public void onActivityResult(int requestCode, Intent data) {
        presenter.onActivityResult(requestCode, data);
    }

    //endregion

    //region ================= BaseView =================

    @Override
    protected void initComponent() {
        UploadScreen.Component component = ScreenScoper.getComponent(ScreenScoper.UPLOAD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void attachedToWindow() {
        viewDataBinding.uploadSelectBtn.setOnClickListener(v -> presenter.onSelectButtonClick());
    }

    @Override
    protected void detachedFromWindow() {
        viewDataBinding.uploadSelectBtn.setOnClickListener(null);
    }

    @Override
    public boolean viewOnBackPressed() {
        return false;
    }

    //endregion
}
