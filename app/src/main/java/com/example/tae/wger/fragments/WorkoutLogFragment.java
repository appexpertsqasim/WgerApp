package com.example.tae.wger.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tae.wger.DI.component.DaggerIActivityComponent;
import com.example.tae.wger.DI.component.IActivityComponent;
import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.R;
import com.example.tae.wger.adapters.WorkoutLogAdapter;
import com.example.tae.wger.listener.WorkoutLogRecyclerViewClickListener;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.WorkoutLogModel;
import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.workoutlog.IWorkoutLogListMvpView;
import com.example.tae.wger.ui.workoutlog.WorkoutLogListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tae.wger.MyApplication.getApplication;
import static com.example.tae.wger.R.id.container;

/**
 * Created by TAE on 23/10/2017.
 */

public class WorkoutLogFragment extends BaseFragment implements IWorkoutLogListMvpView {
    @Inject
    WorkoutLogListPresenter<IWorkoutLogListMvpView> WorkoutListPresenter;
    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }
    @BindView(R.id.log_view)
    RecyclerView recyclerView;
    Integer workoutId;
    String workoutDate;
    @BindView(R.id.save_btn)
    Button workout;
    @BindView(R.id.muscles_btn)
    Button muscle;
    @BindView(R.id.refresh_log)
    SwipeRefreshLayout refresh;
    @BindView(R.id.reps_tv)
    EditText reps;
    @BindView(R.id.weight_tv)
    EditText weight;
    @BindView(R.id.rep_unit_sp)
    Spinner rep_unit;
    @BindView(R.id.weight_unit_sp)
    Spinner weight_unit;
    @BindView(R.id.exercise_tv)
    AutoCompleteTextView exercise;
     ExerciseModel model;
    String[] names ;
    ArrayList<Integer> muscles=new ArrayList<Integer>();
    Integer weightId;
    Integer rep_id;

    public WorkoutLogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        workoutId = getArguments().getInt("CID");
        workoutDate=getArguments().getString("CID2");

        return inflater.inflate(R.layout.workoutlog,container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initialiseDagger();
        ButterKnife.bind(this, view);
      //  WorkoutListPresenter = new WorkoutLogListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        WorkoutListPresenter.onAttach(this);
        WorkoutListPresenter.onViewPreparedExercise();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        model=new ExerciseModel();
        String[] weight_units = {"kg","lb", "Body Weight", "Km per Hour","Miles Per Hour","Pilates"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, weight_units);
        weight_unit.setAdapter(adapter2);
        weight_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> av, View v,
                                       int position, long itemId) {

                // TODO Auto-generated method stub
                weightId=position+1;
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }});
        String[] rep_units = {"Kilometers","Miles", "Minutes", "Repetetions","Seconds","Until failure"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, rep_units);
        rep_unit.setAdapter(adapter1);
        rep_unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> av, View v,
                                       int position, long itemId) {

                // TODO Auto-generated method stub
                rep_id=position+1;
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }});

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                 @Override
                                                 public void onRefresh() {
                                                     WorkoutListPresenter.onViewPrepared(workoutId);

                                                     refresh.setRefreshing(false);
                                                 }
                                             });
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkoutListPresenter.onViewPrepared(Integer.valueOf(reps.getText().toString()),weight.getText().toString(),
                       workoutDate,getExerciseId(model,exercise.getText().toString()), workoutId,
                        rep_id,weightId);
            }
        });
        muscle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fr=new WorkoutMuscles();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putIntegerArrayList("CID", muscles);
                fr.setArguments(args);
                ft.replace(container, fr);
                ft.commit();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onFetchDataCompleted(WorkoutLogModel.Result workoutModel) {
        Log.i("model size", String.valueOf(workoutModel.getExercise()));
        }




    @Override
    public void onFetchDataCompleted(ExerciseModel exerciseModel) {
        model=exerciseModel;
        names = new String[exerciseModel.getResults().size()];
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, names);
        exercise.setAdapter(adapter);
        for(int i = 0; i < exerciseModel.getResults().size(); i++){
            names[i]=exerciseModel.getResults().get(i).getName();

        }
        WorkoutListPresenter.onViewPrepared(workoutId);
        }
    @Override
    public void onFetchDataCompleted(WorkoutLogModel workoutModel) {
        Log.i("model size", String.valueOf(workoutModel.getResults().size()));
        recyclerView.setAdapter(new WorkoutLogAdapter(workoutModel, R.layout.workout_list_item, getContext(), new WorkoutLogRecyclerViewClickListener() {
            @Override
            public void onItemClick(WorkoutLogModel.Result item) {
                Integer cid=item.getExercise();
                Fragment fr=new ExerciseInfoFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("CID", cid);
                fr.setArguments(args);
                ft.replace(container, fr);
                ft.commit();

            }
        }));

        for(int i = 0; i < workoutModel.getResults().size();i++) {
            Log.i("model size check", String.valueOf(workoutModel.getResults().size()));
            getExerciseMuscles(workoutModel.getResults().get(i).getExercise());
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {
       Log.i("errrrrorr",message);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    public int getExerciseId(ExerciseModel exerciseModel,String name){

        int id=0;
        for(int i = 0; i < exerciseModel.getResults().size(); i++){
            if(exerciseModel.getResults().get(i).getName().equals(name)){
                id=exerciseModel.getResults().get(i).getId();
            }
        }
        return id;
    }
    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iAppComponent(((MyApplication) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
    }
    public void getExerciseMuscles (int id){
        Log.i("getEcerciseModel-all",""+model.getCount());
       for(int i = 0; i < model.getResults().size(); i++){

            if(model.getResults().get(i).getId().equals(id)){
               List<Integer> all= model.getResults().get(i).getMuscles();

                for(int m = 0; m < all.size(); m++){
                muscles.add(model.getResults().get(i).getMuscles().get(m));
             }
            }

        }

    }
}
