package com.example.tae.wger.network;


import android.util.Log;

import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.GymMapModel;
import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.model.WorkoutModel;
import com.example.tae.wger.services.ConnectionService;
import com.example.tae.wger.services.MapReqInterface;
import com.example.tae.wger.services.reqInterface;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 *Created by TAE on 19/10/2017.
 */
@Singleton
public class AppApiHelper implements ApiHelper {
    reqInterface reqInterface;
    MapReqInterface mapReqInterface;
    @Inject
    public AppApiHelper() {
        this.reqInterface = ConnectionService.getConnectionService();
        this.mapReqInterface=ConnectionService.getConnectionServiceMap();
    }


    @Override
    public Observable<EquipmentModel> useCaseEquipment() {
        Log.i("~~~~~~~~~~~~~~~~~~~~~", "API CALLED");
        return reqInterface.getEquipment();
    }

    @Override
    public Observable<ExerciseModel> useCaseExercise(int id) {
        return reqInterface.getExercise(id);
    }

    @Override
    public Observable<ExerciseModel> useCaseExercise() {
        return reqInterface.getExercise();
    }

    @Override
    public Observable<WorkoutModel> useCaseworkout() {
        return reqInterface.getWorkout();
    }

    @Override
    public Observable<MuscleModel> useCaseMuscle() {
        return reqInterface.getMuscle();
    }

    @Override
    public Observable<WorkoutModel.Result> usecaseAddWorkout(String comment) {
        Log.i("usecase workout","called");
        return reqInterface.addWorkout(comment);
    }

    @Override
    public Observable<WorkoutLogModel> useCaseWorkoutLog(int id) {
        return reqInterface.getWorkoutLog(id);
    }

    @Override
    public Observable<WorkoutLogModel.Result> usecaseAddWorkoutLog(int reps, String weight, String date, int exercise, int workout, int rep, int weight_unit) {
        return reqInterface.addWorkoutLog(reps,weight,date,exercise,workout,rep,weight_unit);
    }


    @Override
    public Observable<ExerciseInfoModel.Result> useCaseExerciseInfo(int id) {
        return reqInterface.getExerciseInfo(id);
    }

    @Override
    public Observable<GymMapModel> useCaseGyms(String location,String type,String radius,String key) {
        Log.i("Called","app api helper");
        return mapReqInterface.getNearestGyms(location,type,radius,key);
    }
}

