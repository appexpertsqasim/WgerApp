package com.example.tae.wger.DI.module;

import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.equipment.EquipmentListPresenter;
import com.example.tae.wger.ui.equipment.IEquipmentListMvpPresenter;
import com.example.tae.wger.ui.equipment.IEquipmentListMvpView;
import com.example.tae.wger.ui.exercise.ExerciseListPresenter;
import com.example.tae.wger.ui.exercise.IExerciseListMvpPresenter;
import com.example.tae.wger.ui.exercise.IExerciseListMvpView;
import com.example.tae.wger.ui.exercise_info.ExerciseInfoListPresenter;
import com.example.tae.wger.ui.exercise_info.IExerciseInfoListMvpPresenter;
import com.example.tae.wger.ui.exercise_info.IExerciseInfoListMvpView;
import com.example.tae.wger.ui.muscle.IMuscleListMvpPresenter;
import com.example.tae.wger.ui.muscle.IMuscleListMvpView;
import com.example.tae.wger.ui.muscle.MuscleListPresenter;
import com.example.tae.wger.ui.utils.rx.AppSchedulerProvider;
import com.example.tae.wger.ui.utils.rx.SchedulerProvider;
import com.example.tae.wger.ui.workout.IWorkoutListMvpPresenter;
import com.example.tae.wger.ui.workout.IWorkoutListMvpView;
import com.example.tae.wger.ui.workout.WorkoutListPresenter;
import com.example.tae.wger.ui.workoutlog.IWorkoutLogListMvpPresenter;
import com.example.tae.wger.ui.workoutlog.IWorkoutLogListMvpView;
import com.example.tae.wger.ui.workoutlog.WorkoutLogListPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TAE on 26/10/2017.
 */
@Module
public class ActivityModule {
    BaseFragment baseFragment;

    public ActivityModule(BaseFragment baseFragment1) {
        this.baseFragment = baseFragment1;
    }


    @Provides
    BaseFragment getBaseFragment() {
        return baseFragment;
    }

    @Provides
    CompositeDisposable compositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider schedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    IEquipmentListMvpPresenter<IEquipmentListMvpView> iEquipmentListMvpPresenter(EquipmentListPresenter<IEquipmentListMvpView> equipmentListPresenter) {
        return equipmentListPresenter;
    }

    @Provides
    IExerciseListMvpPresenter<IExerciseListMvpView> iExericseListMvpPresenter(ExerciseListPresenter<IExerciseListMvpView> exerciseListPresenter) {
        return exerciseListPresenter;
    }

    @Provides
    IExerciseInfoListMvpPresenter<IExerciseInfoListMvpView> iExericseInfoListMvpPresenter(ExerciseInfoListPresenter<IExerciseInfoListMvpView> exerciseInfoListPresenter) {
        return exerciseInfoListPresenter;
    }

    @Provides
    IMuscleListMvpPresenter<IMuscleListMvpView> iMuscleListMvpPresenter(MuscleListPresenter<IMuscleListMvpView> muscleListPresenter) {
        return muscleListPresenter;
    }

    @Provides
    IWorkoutListMvpPresenter<IWorkoutListMvpView> iWorkoutListMvpPresenter(WorkoutListPresenter<IWorkoutListMvpView> workoutListPresenter) {
        return workoutListPresenter;
    }

    @Provides
    IWorkoutLogListMvpPresenter<IWorkoutLogListMvpView> iWorkoutLogListMvpPresenter(WorkoutLogListPresenter<IWorkoutLogListMvpView> workoutLogListPresenter) {
        return workoutLogListPresenter;
    }

}
