package com.migrsoft.hiltdemo.app;

import android.app.Application;

import com.migrsoft.hiltdemo.api.UserModuleApi;
import com.migrsoft.hiltdemo.http.Interceptor.InterceptForGET;
import com.migrsoft.hiltdemo.http.Interceptor.InterceptForPOST;
import com.migrsoft.hiltdemo.http.WebApiService;
import com.migrsoft.hiltdemo.repository.CacheRepository;
import com.migrsoft.hiltdemo.repository.impl.CacheRepositoryImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    CacheRepository provideCacheRepository(Application application){
        return new CacheRepositoryImpl(application.getCacheDir().getAbsolutePath());
    }

    @Singleton
    @Named("GET")
    @Provides
    InterceptForGET provideInterceptForGET(){
        return new InterceptForGET();
    }

    @Singleton
    @Named("POST")
    @Provides
    InterceptForPOST provideInterceptForPOST(){
        return new InterceptForPOST();
    }

    @Singleton
    @Provides
    UserModuleApi provideUserModuleApi(@Named("POST") Interceptor interceptor){
        return WebApiService.generateApi(UserModuleApi.class,interceptor);
    }
}
