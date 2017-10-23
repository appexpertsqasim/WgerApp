package com.example.tae.wger.ui.exercise_info;


import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 29/09/2017.
 */

public interface IExerciseInfoListMvpView extends MvpView {
    void onFetchDataCompleted(ExerciseInfoModel.Result exerciseInfoModel);
}
