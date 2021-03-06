package com.smartjump.app.di.module;

import android.content.Context;

import com.smartjump.app.di.LifeScope;
import com.smartjump.app.repo.DefaultDataRepository;
import com.smartjump.data.remote.RemoteDataStore;
import com.smartjump.data.remote.SmartJumpApi;
import com.smartjump.domain.DataRepository;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
@Module
public class NetworkModule {

    private final String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @LifeScope
    @Provides
    Cache provideCache(Context application) {
        final int cacheSize = 1024 * 1024 * 5;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @LifeScope
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @LifeScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @LifeScope
    @Provides
    SmartJumpApi provideApi(Retrofit retrofit) {
        return retrofit.create(SmartJumpApi.class);
    }

    @LifeScope
    @Provides
    RemoteDataStore provideRemoteDataStore(SmartJumpApi smartJumpApi) {
        return new RemoteDataStore(smartJumpApi);
    }

    @LifeScope
    @Provides
    DataRepository provideDataRepository(RemoteDataStore remoteDataStore) {
        return new DefaultDataRepository(remoteDataStore);
    }
}
