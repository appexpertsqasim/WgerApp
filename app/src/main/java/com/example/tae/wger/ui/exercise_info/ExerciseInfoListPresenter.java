package com.example.tae.wger.ui.exercise_info;


import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.network.DataManager;
import com.example.tae.wger.ui.base.BasePresenter;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseInfoListPresenter<V extends IExerciseInfoListMvpView> extends BasePresenter<V> implements IExerciseInfoListMvpPresenter<V> {


    public ExerciseInfoListPresenter(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared(int id) {
        Log.i("On View Prepared exercise info","called" );
        getCompositeDisposable()
                .add(getDataManager().useCaseExerciseInfo(id)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<ExerciseInfoModel.Result>() {
                                       @Override
                                       public void accept(@NonNull ExerciseInfoModel.Result exerciseInfoModel) throws Exception {
                                           getMvpView().onFetchDataCompleted(exerciseInfoModel);
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
