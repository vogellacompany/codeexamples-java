package com.vogella.java.dagger2.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import com.vogella.java.dagger2.BackendService;
import com.vogella.java.dagger2.User;

import dagger.Module;
import dagger.Provides;

@Module
public class BackEndServiceModule {
    
    @Provides
    @Singleton
    BackendService provideBackendService(User user, @Named("serverUrl") String serverUrl) {
       return new BackendService(user, serverUrl);
    }
    
    @Provides
    @Named("serverUrl")
    String provideServerUrl() {
       return "http://www.vogella.com";
    }
    
    @Provides
    @Named("anotherUrl")
    String provideAnotherUrl() {
       return "http://www.google.com";
    }

}