package com.vogella.java.dagger2.component;

import javax.inject.Singleton;

import com.vogella.java.dagger2.BackendService;
import com.vogella.java.dagger2.modules.BackEndServiceModule;
import com.vogella.java.dagger2.modules.UserModule;

import dagger.Component;

@Singleton
@Component(modules = { UserModule.class, BackEndServiceModule.class })
public interface MyComponent {
	BackendService createBackendService();
}
