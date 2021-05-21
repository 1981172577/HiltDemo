package com.migrsoft.hiltdemo.module.login;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final LoginRepository loginRepository;

    @Inject
    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public String getStr(){
        return loginRepository.getStr();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        loginRepository.release();
    }
}
