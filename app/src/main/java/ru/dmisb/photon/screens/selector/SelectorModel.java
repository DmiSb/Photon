package ru.dmisb.photon.screens.selector;

import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.flow.ScreenScoper;

public class SelectorModel extends BaseModel {

    @Override
    protected void initComponent() {
        SelectorScreen.Component component = ScreenScoper.getComponent(ScreenScoper.SELECTOR_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }
}
