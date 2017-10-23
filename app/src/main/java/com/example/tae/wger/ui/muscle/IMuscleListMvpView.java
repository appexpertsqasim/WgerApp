package com.example.tae.wger.ui.muscle;


import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 29/09/2017.
 */

public interface IMuscleListMvpView extends MvpView {
    void onFetchDataCompleted(MuscleModel muscleeModel);
}
