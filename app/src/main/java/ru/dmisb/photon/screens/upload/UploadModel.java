package ru.dmisb.photon.screens.upload;

import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.flow.ScreenScoper;

public class UploadModel extends BaseModel {

    //region ================= BaseModel =================

    @Override
    protected void initComponent() {
        UploadScreen.Component component = ScreenScoper.getComponent(ScreenScoper.UPLOAD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    //endregion
}
