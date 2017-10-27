package com.example.tae.wger.ui.gyms;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tae.wger.model.GymMapModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 26/10/2017.
 */

public class GymPresenter<V extends IGymMvpView> extends BasePresenter<V> implements IGymMvpPresenter<V> {

    @Inject
    public GymPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(String location,String type,String radius,String key) {
        Log.i("On View Prepared","called" );
        getCompositeDisposable()
                .add(getDataManager().useCaseGyms(location,type,radius,key)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<GymMapModel>() {
                                       @Override
                                       public void accept(@NonNull GymMapModel gymMapModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(gymMapModel);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {
                                        getMvpView().onError(throwable.getMessage());
                                    }
                                }));

    }


}
