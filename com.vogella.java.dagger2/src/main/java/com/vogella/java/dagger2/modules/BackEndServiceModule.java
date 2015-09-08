package com.vogella.java.dagger2.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import com.vogella.java.dagger2.BackendService;

import dagger.Module;
import dagger.Provides;

@Module
public class BackEndServiceModule {
    
    @Provides
    @Singleton
    BackendService provideBackendService(@Named("serverUrl") String serverUrl) {
       return new BackendService(serverUrl);
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