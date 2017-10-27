package com.example.tae.wger.ui.workoutlog;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 23/10/2017.
 */

public interface IWorkoutLogListMvpPresenter<V extends IWorkoutLogListMvpView> extends MvpPresenter<V> {
    void onViewPrepared(int reps, String weight, String date, int exercise, int workout, int rep, int weight_unit);
    void onViewPrepared(int id);
    void onViewPreparedExercise();
}
