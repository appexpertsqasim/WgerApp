package com.example.tae.wger.ui.workout;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 23/10/2017.
 */

public interface IWorkoutListMvpPresenter<V extends IWorkoutListMvpView> extends MvpPresenter<V> {
    void onViewPrepared(String comment);
    void onViewPrepared();
}
