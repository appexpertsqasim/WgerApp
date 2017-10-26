package com.example.tae.wger;

import android.app.Application;
import android.content.Context;

import com.example.tae.wger.DI.component.DaggerIAppComponent;
import com.example.tae.wger.DI.component.IAppComponent;
import com.example.tae.wger.DI.module.ApplicationModule;


public class MyApplication extends Application {

    IAppComponent iAppComponent;

    public static Application sApplication;

    public IAppComponent getiApplicationComponent() {
        return iAppComponent;
    }
    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }
    public IAppComponent getIappComponent() {
        return iAppComponent;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        iAppComponent= DaggerIAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        getIappComponent().inject(this);

    }

}