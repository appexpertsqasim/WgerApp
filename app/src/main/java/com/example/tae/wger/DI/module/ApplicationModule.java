package com.example.tae.wger.DI.module;

import android.app.Application;
import android.content.Context;

import com.example.tae.wger.DI.scope.ApplicationContext;
import com.example.tae.wger.network.ApiHelper;
import com.example.tae.wger.network.AppApiHelper;
import com.example.tae.wger.network.AppDataManager;
import com.example.tae.wger.network.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TAE on 03/10/2017.
 */
@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context context() {
        return application;
    }

    @Provides
    Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideAppDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideAppApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
}