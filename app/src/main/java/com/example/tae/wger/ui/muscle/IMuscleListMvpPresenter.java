package com.example.tae.wger.ui.muscle;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 19/10/2017.
 */

public interface IMuscleListMvpPresenter<V extends IMuscleListMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
