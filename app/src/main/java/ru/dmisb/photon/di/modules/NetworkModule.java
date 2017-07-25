package ru.dmisb.photon.di.modules;

import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import ru.dmisb.photon.data.network.RestService;
import ru.dmisb.photon.data.network.adapters.DateAdapter;
import ru.dmisb.photon.utils.AppConfig;

@SuppressWarnings("unused")
@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient () {
        return createClient();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit (OkHttpClient okHttp) {
        return createRetrofit(okHttp);
    }

    @Provides
    @Singleton
    RestService provideRestService (Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(AppConfig.MAX_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(AppConfig.MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(AppConfig.MAX_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    private Retrofit createRetrofit(OkHttpClient okHttp) {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(createConvertFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //add rx adapter
                .client(okHttp)
                .build();
    }

    private Converter.Factory createConvertFactory() {
        return MoshiConverterFactory.create(new Moshi.Builder()
                .add(new DateAdapter())
                .build());
    }
}
