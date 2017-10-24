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
import android.widget.Button;

import com.example.tae.wger.R;
import com.example.tae.wger.adapters.WorkoutAdapter;
import com.example.tae.wger.listener.WorkoutRecyclerViewClickListener;
import com.example.tae.wger.model.WorkoutModel;
import com.example.tae.wger.network.AppDataManager;
import com.example.tae.wger.ui.utils.rx.AppSchedulerProvider;
import com.example.tae.wger.ui.workout.IWorkoutListMvpView;
import com.example.tae.wger.ui.workout.WorkoutListPresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TAE on 23/10/2017.
 */

public class WorkoutFragment extends Fragment implements IWorkoutListMvpView {
    WorkoutListPresenter<IWorkoutListMvpView> WorkoutListPresenter;
    RecyclerView recyclerView;
    Button workout;
    SwipeRefreshLayout refresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.workout_view_layout,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WorkoutListPresenter = new WorkoutListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        WorkoutListPresenter.onAttach(this);
        WorkoutListPresenter.onViewPrepared();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        workout=(Button)view.findViewById(R.id.workout_btn);
        refresh=(SwipeRefreshLayout)view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                                 @Override
                                                 public void onRefresh() {
                                                     WorkoutListPresenter.onViewPrepared();

                                                     refresh.setRefreshing(false);
                                                 }
                                             });
        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkoutListPresenter.onViewPrepared("Created in android studio");
            }
        });

    }

    @Override
    public void onFetchDataCompleted(WorkoutModel.Result workoutModel) {

    }

    @Override
    public void onFetchDataCompleted(WorkoutModel workoutModel) {
        Log.i("OnFetchCalled", "passseddd");

        recyclerView.setAdapter(new WorkoutAdapter(workoutModel, R.layout.workout_list_item, getActivity().getApplicationContext(), new WorkoutRecyclerViewClickListener() {
            @Override
            public void onItemClick(WorkoutModel.Result item) {
                Integer cid=item.getId();
                Fragment fr=new ExerciseFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("CID", cid);
                fr.setArguments(args);
                ft.replace(R.id.container, fr);
                ft.commit();

            }
        }));

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
}