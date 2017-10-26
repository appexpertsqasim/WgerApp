package com.example.tae.wger.network;


import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.model.WorkoutModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by TAE on 19/10/2017.
 */
@Singleton
public class AppDataManager implements DataManager {
    ApiHelper apiHelper;
    @Inject
    public AppDataManager(){this.apiHelper=new AppApiHelper();}
    @Override
    public Observable<EquipmentModel> useCaseEquipment() {
        return apiHelper.useCaseEquipment();
    }

    @Override
    public Observable<ExerciseModel> useCaseExercise(int id) {
        return apiHelper.useCaseExercise(id);
    }
    @Override
    public Observable<ExerciseModel> useCaseExercise() {
        return apiHelper.useCaseExercise();
    }

    @Override
    public Observable<WorkoutModel> useCaseworkout() {
        return apiHelper.useCaseworkout();
    }

    @Override
    public Observable<MuscleModel> useCaseMuscle() {
        return apiHelper.useCaseMuscle();
    }

    @Override
    public Observable<WorkoutModel.Result> usecaseAddWorkout(String comment) {
        return apiHelper.usecaseAddWorkout(comment);
    }

    @Override
    public Observable<WorkoutLogModel> useCaseWorkoutLog(int id) {
        return apiHelper.useCaseWorkoutLog(id);
    }

    @Override
    public Observable<WorkoutLogModel.Result> usecaseAddWorkoutLog(int reps, String weight, String date, int exercise, int workout, int rep, int weight_unit) {
        return apiHelper.usecaseAddWorkoutLog(reps,weight,date,exercise,workout,rep,weight_unit);
    }

    @Override
    public Observable<ExerciseInfoModel.Result> useCaseExerciseInfo(int id) {
        return apiHelper.useCaseExerciseInfo(id);
    }


}
