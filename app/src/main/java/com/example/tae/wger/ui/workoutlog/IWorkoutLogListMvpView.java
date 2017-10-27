package com.example.tae.wger.ui.workoutlog;


import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 23/10/2017.
 */

public interface IWorkoutLogListMvpView extends MvpView {
    void onFetchDataCompleted(WorkoutLogModel.Result workoutModel);
    void onFetchDataCompleted(WorkoutLogModel workoutModel);
    void onFetchDataCompleted(ExerciseModel exerciseModel);
}
