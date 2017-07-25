package ru.dmisb.photon.screens.root;

import javax.inject.Inject;

import ru.dmisb.photon.data.repo.Repository;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.ScreenScoper;

public class RootModel {

    @Inject
    Repository repository;

    public RootModel() {
        initComponent();
    }

    //region ================= DI =================

    private void initComponent() {
        RootComponent rootComponent = ScreenScoper.getComponent(ScreenScoper.ROOT_SCOPE_NAME);
        if (rootComponent != null)
            rootComponent.inject(this);
    }

    //endregion
}
