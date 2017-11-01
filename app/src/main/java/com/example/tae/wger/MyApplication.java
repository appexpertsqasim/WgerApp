package com.example.tae.wger;

import android.app.Application;
import android.content.Context;

import com.example.tae.wger.DI.component.DaggerIAppComponent;
import com.example.tae.wger.DI.component.IAppComponent;
import com.example.tae.wger.DI.module.ApplicationModule;
import com.example.tae.wger.LocalDB.realm_controller.RealmController;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {

    IAppComponent iAppComponent;
    RealmController controller;
    Realm realm;

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
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration= new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm=Realm.getDefaultInstance();
        controller=new RealmController(realm);
        controller.deleteDatabase();

    }

}