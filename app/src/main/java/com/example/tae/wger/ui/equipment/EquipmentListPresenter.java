package com.example.tae.wger.ui.equipment;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 19/10/2017.
 */

public class EquipmentListPresenter<V extends IEquipmentListMvpView> extends BasePresenter<V> implements IEquipmentListMvpPresenter<V> {


    public EquipmentListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        Log.i("On View Prepared","called" );
        getCompositeDisposable()
                .add(getDataManager().useCaseEquipment()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<EquipmentModel>() {
                                       @Override
                                       public void accept(@NonNull EquipmentModel equipmentModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(equipmentModel);
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
