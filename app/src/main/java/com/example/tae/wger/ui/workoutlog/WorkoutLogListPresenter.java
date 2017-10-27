package com.example.tae.wger.ui.workoutlog;


import android.support.annotation.NonNull;

import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 23/10/2017.
 */

public class WorkoutLogListPresenter<V extends IWorkoutLogListMvpView> extends BasePresenter<V> implements IWorkoutLogListMvpPresenter<V> {

    @Inject
    public WorkoutLogListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
    @Override
    public void onViewPreparedExercise() {

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

    @Override
    public void onViewPrepared(int reps, String weight, String date, int exercise, int workout, int rep, int weight_unit) {

        getCompositeDisposable()
                .add(getDataManager().usecaseAddWorkoutLog(reps,weight,date,exercise,workout,rep,weight_unit)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<WorkoutLogModel.Result>() {
                                       @Override
                                       public void accept(@NonNull WorkoutLogModel.Result workoutModel) throws Exception {
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
    public void onViewPrepared(int id) {

        getCompositeDisposable()
                .add(getDataManager().useCaseWorkoutLog(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<WorkoutLogModel >() {
                                       @Override
                                       public void accept(@NonNull WorkoutLogModel workoutModel) throws Exception {
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
}



