package com.example.tae.wger.DI.component;


import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.DI.scope.PerActivity;
import com.example.tae.wger.fragments.EquipmentFragment;
import com.example.tae.wger.fragments.ExerciseFragment;
import com.example.tae.wger.fragments.ExerciseInfoFragment;
import com.example.tae.wger.fragments.MuscleFragment;
import com.example.tae.wger.fragments.WorkoutFragment;
import com.example.tae.wger.fragments.WorkoutLogFragment;
import com.example.tae.wger.fragments.WorkoutMuscles;

import dagger.Component;

/**
 * Created by TAE on 26/10/2017.
 */
@PerActivity
@Component(dependencies = IAppComponent.class ,modules= ActivityModule.class)
public interface IActivityComponent {
    void inject(EquipmentFragment equipmentFragment);
    void inject(ExerciseFragment  exerciseFragment);
    void inject(ExerciseInfoFragment exerciseInfoFragment);
    void inject (MuscleFragment muscleFragment);
    void inject (WorkoutFragment workoutFragment);
    void inject (WorkoutLogFragment workoutLogFragment);
    void inject(WorkoutMuscles workoutMuscles);

}
