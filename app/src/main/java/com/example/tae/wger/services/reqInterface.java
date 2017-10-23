package com.example.tae.wger.services;


import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.model.WorkoutModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TheAppExperts on 19/10/2017.
 */

public interface reqInterface {
    @Headers({
            ApiConstants.HEADER_AUTHORIZATION,
    })
    @GET(ApiConstants.BASE_URL)
    Observable<WorkoutModel> getWorkout();

    @GET(ApiConstants.Equipment)
    Observable<EquipmentModel> getEquipment();
    @GET(ApiConstants.Exercise)
    Observable<ExerciseModel> getExercise(@Query("equipment") int id);
    @GET(ApiConstants.Exercise)
    Observable<ExerciseModel> getExercise();
    @GET(ApiConstants.Muscle)
    Observable<MuscleModel> getMuscle();
    @GET(ApiConstants.ExerciseInfo)
    Observable<ExerciseInfoModel.Result> getExerciseInfo(@Path("id") int id);


}
