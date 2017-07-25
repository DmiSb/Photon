package ru.dmisb.photon.screens.selector;

import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.flow.ScreenScoper;

public class SelectorPresenter extends BasePresenter<SelectorView, SelectorModel> {

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        SelectorScreen.Component component = ScreenScoper.getComponent(ScreenScoper.SELECTOR_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initActionBar() {
        if (rootPresenter != null && getView() != null) {
            rootPresenter.newBarBuilder()
                    .setToolbarVisible(false)
                    .setBottomBarVisible(true)
                    .setTab(getView().getViewPager())
                    .build();
        }
    }

    //endregion



}
