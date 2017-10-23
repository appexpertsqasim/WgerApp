package com.example.tae.wger.ui.muscle;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 19/10/2017.
 */

public class MuscleListPresenter<V extends IMuscleListMvpView> extends BasePresenter<V> implements IMuscleListMvpPresenter<V> {


    public MuscleListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onViewPrepared() {
        Log.i("On View Prepared","called" );
        getCompositeDisposable()
                .add(getDataManager().useCaseMuscle()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<MuscleModel>() {
                                       @Override
                                       public void accept(@NonNull MuscleModel muscleModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(muscleModel);
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
