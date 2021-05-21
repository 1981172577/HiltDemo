package com.migrsoft.hiltdemo.app;

import android.app.Application;

import com.migrsoft.hiltdemo.api.LoginApi;
import com.migrsoft.hiltdemo.api.UserModuleApi;
import com.migrsoft.hiltdemo.http.Interceptor.InterceptForPOST;
import com.migrsoft.hiltdemo.http.WebApiService;
import com.migrsoft.hiltdemo.repository.CacheRepository;
import com.migrsoft.hiltdemo.repository.impl.CacheRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Singleton
    @Provides
    CacheRepository provideCacheRepository(Application application){
        return new CacheRepositoryImpl(application.getCacheDir().getAbsolutePath());
    }

    @Singleton
    @Provides
    UserModuleApi provideUserModuleApi(InterceptForPOST interceptForPOST){
        return WebApiService.generateApi(UserModuleApi.class,interceptForPOST);
    }

    @Singleton
    @Provides
    LoginApi provideLoginApi(InterceptForPOST interceptForPOST){
        return WebApiService.generateApi(LoginApi.class,interceptForPOST);
    }
}
