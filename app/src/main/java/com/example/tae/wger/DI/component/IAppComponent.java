package com.example.tae.wger.DI.component;

import android.app.Application;

import com.example.tae.wger.DI.module.ApplicationModule;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.network.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by TAE on 26/10/2017.
 */
@Singleton
@Component(modules=ApplicationModule.class)
public interface IAppComponent {
    void inject(MyApplication myApplication);
    Application getApplication();
    DataManager getDataManager();
}
