package com.example.tae.wger.ui.exercise;


import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 29/09/2017.
 */

public interface IExerciseListMvpView extends MvpView {
    void onFetchDataCompleted(ExerciseModel exerciseModel);
}
