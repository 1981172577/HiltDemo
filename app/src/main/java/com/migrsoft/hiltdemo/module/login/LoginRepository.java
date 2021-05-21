package com.migrsoft.hiltdemo.module.login;

import com.migrsoft.hiltdemo.api.LoginApi;
import com.migrsoft.hiltdemo.base.BaseRepository;
import com.migrsoft.hiltdemo.repository.CacheRepository;

import javax.inject.Inject;

public class LoginRepository extends BaseRepository {

    private final LoginApi loginApi;

    @Inject
    public LoginRepository(CacheRepository cacheRepository,LoginApi loginApi) {
        super(cacheRepository);
        this.loginApi = loginApi;
    }

    public String getStr(){
        return "hahahhahhah";
    }


}
