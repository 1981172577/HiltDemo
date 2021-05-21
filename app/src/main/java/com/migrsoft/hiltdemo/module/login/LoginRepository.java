package com.migrsoft.hiltdemo.module.login;

import com.migrsoft.hiltdemo.api.UserModuleApi;
import com.migrsoft.hiltdemo.base.BaseRepository;
import com.migrsoft.hiltdemo.repository.CacheRepository;

import javax.inject.Inject;

public class LoginRepository extends BaseRepository {

    private final UserModuleApi userModuleApi;

    @Inject
    public LoginRepository(CacheRepository cacheRepository, UserModuleApi userModuleApi) {
        super(cacheRepository);
        this.userModuleApi = userModuleApi;
    }


}
