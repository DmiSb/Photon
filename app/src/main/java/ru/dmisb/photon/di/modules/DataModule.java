package ru.dmisb.photon.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.dmisb.photon.data.repo.Repository;

@SuppressWarnings("unused")
@Module
public class DataModule {
    @Provides
    @Singleton
    Repository provideRepository() {
       return new Repository();
    }
}