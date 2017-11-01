package com.example.tae.wger.ui.exercise;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 19/10/2017.
 */

public interface IExerciseListMvpPresenter<V extends IExerciseListMvpView> extends MvpPresenter<V> {
    void onViewPrepared(int id);
    void onViewPrepared();
    void onViewPreparedPage(String page);
//    void onViewPreparedZip();

}
