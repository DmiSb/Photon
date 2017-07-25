package ru.dmisb.photon.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.screens.root.IRootPresenter;
import ru.dmisb.photon.screens.root.RootActivity;
import ru.dmisb.photon.screens.root.RootModel;
import ru.dmisb.photon.screens.root.RootPresenter;

@Module
public class RootModule {
    @Provides
    @DaggerScope(RootActivity.class)
    IRootPresenter provideIRootPresenter() {
       return new RootPresenter();
    }

    @Provides
    @DaggerScope(RootActivity.class)
    RootModel provideRootModel() {
       return new RootModel();
    }
}