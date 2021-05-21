package com.migrsoft.hiltdemo.module.login;

import com.migrsoft.hiltdemo.api.UserModuleApi;
import com.migrsoft.hiltdemo.repository.CacheRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityRetainedScoped;

//@Module
//@InstallIn(ActivityComponent.class)
//public abstract class LoginModule {
//
//    @Binds
//    abstract LoginRepository provideLoginRepository(CacheRepository cacheRepository, UserModuleApi userModuleApi);
//
//}
