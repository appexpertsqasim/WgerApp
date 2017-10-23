package com.example.tae.wger.ui.exercise_info;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 19/10/2017.
 */

public interface IExerciseInfoListMvpPresenter<V extends IExerciseInfoListMvpView> extends MvpPresenter<V> {
    void onViewPrepared(int id);
}
