package com.vogella.java.dagger2.modules;

import javax.inject.Singleton;

import com.vogella.java.dagger2.User;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    @Singleton
    User providesUser() {
    	return new User("Lars", "Vogel");
    }
}