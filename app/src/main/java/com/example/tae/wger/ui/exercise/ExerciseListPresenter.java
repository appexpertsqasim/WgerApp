package com.example.tae.wger.ui.exercise;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseListPresenter<V extends IExerciseListMvpView> extends BasePresenter<V> implements IExerciseListMvpPresenter<V> {


    public ExerciseListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(int id) {
        Log.i("On View Prepared","called" );
        getCompositeDisposable()
                .add(getDataManager().useCaseExercise(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<ExerciseModel>() {
                                       @Override
                                       public void accept(@NonNull ExerciseModel exerciseModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(exerciseModel);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {
                                        getMvpView().onError(throwable.getMessage());
                                    }
                                }));

    }
    @Override
    public void onViewPrepared() {
        Log.i("On View Prepared","called" );
        getCompositeDisposable()
                .add(getDataManager().useCaseExercise()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<ExerciseModel>() {
                                       @Override
                                       public void accept(@NonNull ExerciseModel exerciseModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(exerciseModel);
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
