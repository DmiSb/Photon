package ru.dmisb.photon.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.dmisb.photon.data.local.PreferencesManager;
import ru.dmisb.photon.data.storage.StorageManager;

@Module
public class LocalModule {
    @Provides
    @Singleton
    PreferencesManager providePreferencesManager(Context context) {
       return new PreferencesManager(context);
    }

    @Provides
    @Singleton
    StorageManager provideStorageManager() {
       return new StorageManager();
    }
}