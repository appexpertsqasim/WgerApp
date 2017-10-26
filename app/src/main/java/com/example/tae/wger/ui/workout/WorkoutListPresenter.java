package com.example.tae.wger.ui.workout;


import android.support.annotation.NonNull;

import com.example.tae.wger.model.WorkoutModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 23/10/2017.
 */

public class WorkoutListPresenter<V extends IWorkoutListMvpView> extends BasePresenter<V> implements IWorkoutListMvpPresenter<V> {

     @Inject
    public WorkoutListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onViewPrepared(String comment) {

        getCompositeDisposable()
                .add(getDataManager().usecaseAddWorkout(comment)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<WorkoutModel.Result>() {
                                       @Override
                                       public void accept(@NonNull WorkoutModel.Result workoutModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(workoutModel);
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

        getCompositeDisposable()
                .add(getDataManager().useCaseworkout()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<WorkoutModel >() {
                                       @Override
                                       public void accept(@NonNull WorkoutModel workoutModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(workoutModel);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(@NonNull Throwable throwable) throws Exception {
                                        getMvpView().onError(throwable.getMessage());
                                    }
                                }));


    }}


