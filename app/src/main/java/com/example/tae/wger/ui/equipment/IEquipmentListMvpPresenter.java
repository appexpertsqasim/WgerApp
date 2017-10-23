package com.example.tae.wger.ui.equipment;


import com.example.tae.wger.ui.base.MvpPresenter;

/**
 * Created by TAE on 19/10/2017.
 */

public interface IEquipmentListMvpPresenter<V extends IEquipmentListMvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
