package ru.dmisb.photon.di.modules;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.dmisb.photon.App;
import ru.dmisb.photon.data.repo.Repository;
import ru.dmisb.photon.job.BaseJob;
import ru.dmisb.photon.utils.AppConfig;

@SuppressWarnings("unused")
@Module
public class DataModule {
    @Provides
    @Singleton
    Repository provideRepository() {
       return new Repository();
    }

    @Provides
    JobManager provideJobManager() {
        Configuration configuration = new Configuration.Builder(App.getAppContext())
                .minConsumerCount(AppConfig.MIN_CONSUMER_COUNT)
                .maxConsumerCount(AppConfig.MAX_CONSUMER_COUNT)
                .loadFactor(AppConfig.LOAD_FACTOR)
                .consumerKeepAlive(AppConfig.CONSUMER_KEEP_ALIVE)
                .injector(job -> App.getDataComponent().inject((BaseJob) job))
                .build();

        return new JobManager(configuration);
    }
}