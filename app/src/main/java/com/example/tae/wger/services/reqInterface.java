package com.example.tae.wger.services;


import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.model.WorkoutModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TheAppExperts on 19/10/2017.
 */

public interface reqInterface {
    @Headers({
            ApiConstants.HEADER_AUTHORIZATION,
    })
    @FormUrlEncoded
    @POST(ApiConstants.Workout)
    Observable<WorkoutModel.Result> addWorkout(@Field("comment") String comment);
    @Headers({
            ApiConstants.HEADER_AUTHORIZATION,
    })

    @FormUrlEncoded
    @POST(ApiConstants.WorkoutLog)
    Observable<WorkoutLogModel.Result> addWorkoutLog(@Field("reps") int reps,
                                                  @Field("weight") String weight,
                                                  @Field("date") String date,
                                                  @Field("exercise") int exercise,
                                                  @Field("workout") int workout,
                                                  @Field("repetition_unit") int rep,
                                                  @Field("weight_unit") int weight_unit)          ;
    @GET(ApiConstants.Equipment)
    Observable<EquipmentModel> getEquipment();
    @Headers({
            ApiConstants.HEADER_AUTHORIZATION,
    })
    @GET(ApiConstants.Workout)
    Observable<WorkoutModel> getWorkout();
    @Headers({
            ApiConstants.HEADER_AUTHORIZATION,
    })
    @GET(ApiConstants.WorkoutLog)
    Observable<WorkoutLogModel> getWorkoutLog();
    @GET(ApiConstants.Exercise)
    Observable<ExerciseModel> getExercise(@Query("equipment") int id);
    @GET(ApiConstants.Exercise)
    Observable<ExerciseModel> getExercise();
    @GET(ApiConstants.Muscle)
    Observable<MuscleModel> getMuscle();
    @GET(ApiConstants.ExerciseInfo)
    Observable<ExerciseInfoModel.Result> getExerciseInfo(@Path("id") int id);


}
