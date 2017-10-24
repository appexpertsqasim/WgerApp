package com.example.tae.wger.ui.workout;


import com.example.tae.wger.model.WorkoutModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 23/10/2017.
 */

public interface IWorkoutListMvpView extends MvpView {
    void onFetchDataCompleted(WorkoutModel.Result workoutModel);
    void onFetchDataCompleted(WorkoutModel workoutModel);
}
