package ru.dmisb.photon.di.components;

import javax.inject.Singleton;

import dagger.Subcomponent;
import ru.dmisb.photon.data.repo.Repository;
import ru.dmisb.photon.di.modules.DataModule;
import ru.dmisb.photon.di.modules.LocalModule;
import ru.dmisb.photon.di.modules.NetworkModule;
import ru.dmisb.photon.di.modules.RootModule;
import ru.dmisb.photon.job.BaseJob;

@Subcomponent(modules = {DataModule.class, LocalModule.class, NetworkModule.class})
@Singleton
public interface DataComponent {
    RootComponent plus(RootModule module);
    void inject(Repository repository);
    void inject(BaseJob job);
}
