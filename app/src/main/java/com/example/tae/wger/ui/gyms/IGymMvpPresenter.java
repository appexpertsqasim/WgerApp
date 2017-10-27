package com.example.tae.wger.ui.gyms;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 19/10/2017.
 */

public interface IGymMvpPresenter<V extends IGymMvpView> extends MvpPresenter<V> {
    void onViewPrepared(String location,String type,String radius,String key);
}
