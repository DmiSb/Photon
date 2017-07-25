package ru.dmisb.photon;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import dagger.Provides;
import io.realm.Realm;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import ru.dmisb.photon.di.components.DataComponent;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.di.modules.DataModule;
import ru.dmisb.photon.di.modules.RootModule;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.utils.FormatUtils;

@SuppressWarnings("unused")
public class App extends Application {

    private static Context appContext;
    private static DataComponent dataComponent;
    private MortarScope appScope;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        appContext = getApplicationContext();
        initDaggerComponents();

        FormatUtils.initFormats();

        if (BuildConfig.DEBUG)
            initStetho();
    }

    @Override
    public Object getSystemService(String name) {
        if (appScope != null) {
            return appScope.hasService(name) ? appScope.getService(name) : super.getSystemService(name);
        } else {
            return super.getSystemService(name);
        }
    }

    public static Context getAppContext() {
        return appContext;
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(appContext)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(appContext))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(appContext).build())
                        .build()
        );
    }

    // endregion

    //region ================= DI =================

    private void initDaggerComponents() {
        AppComponent appComponent = DaggerApp_AppComponent.builder()
                .appModule(new AppModule(appContext))
                .build();

        dataComponent = appComponent.plus(new DataModule());
        RootComponent rootComponent = dataComponent.plus(new RootModule());

        appScope = MortarScope.buildRootScope()
                .withService(ScreenScoper.SCOPE_SERVICE_NAME, appComponent)
                .build(ScreenScoper.APP_SCOPE_NAME);

        MortarScope rootScope = appScope.buildChild()
                .withService(ScreenScoper.SCOPE_SERVICE_NAME, rootComponent)
                .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                .build(ScreenScoper.ROOT_SCOPE_NAME);

        ScreenScoper.registerScope(rootScope);
    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    @SuppressWarnings("unused")
    @dagger.Module
    public class AppModule {

        private Context context;

        AppModule(Context context) {
            this.context = context;
        }

        @Provides
        Context provideContext () {
            return context;
        }
    }

    @SuppressWarnings("unused")
    @dagger.Component(modules = AppModule.class)
    public interface AppComponent {
        Context getContext();
        DataComponent plus(DataModule module);
    }

    //endregion
}