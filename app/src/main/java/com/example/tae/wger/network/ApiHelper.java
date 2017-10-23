package com.example.tae.wger.network;


import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.MuscleModel;

import io.reactivex.Observable;

/**
 Created by TAE on 19/10/2017.
 */

public interface ApiHelper {
Observable<EquipmentModel> useCaseEquipment();
    Observable<ExerciseModel> useCaseExercise(int id);
    Observable<ExerciseModel> useCaseExercise();
    Observable<MuscleModel> useCaseMuscle();
    Observable<ExerciseInfoModel.Result> useCaseExerciseInfo(int id);
}
