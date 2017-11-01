package com.example.tae.wger.network;


import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.model.ExerciseImageModel;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.GymMapModel;
import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.model.WorkoutModel;

import io.reactivex.Observable;

/**
 Created by TAE on 19/10/2017.
 */

public interface ApiHelper {
    Observable<EquipmentModel> useCaseEquipment();

    Observable<ExerciseModel> useCaseExercise(int id);

    Observable<ExerciseModel> useCaseExercise();
    Observable<ExerciseImageModel> useCaseExerciseImage();
    Observable<ExerciseModel> useCaseExercisePage(String page);
    Observable<ExerciseImageModel> useCaseExerciseImagePage(String page);

    Observable<WorkoutModel> useCaseworkout();

    Observable<MuscleModel> useCaseMuscle();

    Observable<WorkoutModel.Result> usecaseAddWorkout(String comment);

    Observable<WorkoutLogModel> useCaseWorkoutLog(int id);

    Observable<WorkoutLogModel.Result> usecaseAddWorkoutLog(int reps, String weight, String date,
                                                            int exercise, int workout, int rep, int weight_unit);

    Observable<ExerciseInfoModel.Result> useCaseExerciseInfo(int id);

    Observable<GymMapModel> useCaseGyms(String location, String type, String radius, String key);


}
