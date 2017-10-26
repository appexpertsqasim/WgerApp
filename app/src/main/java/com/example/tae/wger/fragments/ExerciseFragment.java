package com.example.tae.wger.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tae.wger.DI.component.DaggerIActivityComponent;
import com.example.tae.wger.DI.component.IActivityComponent;
import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.R;
import com.example.tae.wger.adapters.ExerciseAdapter;
import com.example.tae.wger.listener.ExerciseRecyclerViewClickListener;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.exercise.ExerciseListPresenter;
import com.example.tae.wger.ui.exercise.IExerciseListMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tae.wger.MyApplication.getApplication;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseFragment extends BaseFragment implements IExerciseListMvpView {
    @Inject
    ExerciseListPresenter<IExerciseListMvpView> exerciseListPresenter;
    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Integer equipmentId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if( getArguments()!=null){
            equipmentId = getArguments().getInt("CID");
        }

        return inflater.inflate(R.layout.recycler_view_layout,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initialiseDagger();
       // exerciseListPresenter = new ExerciseListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        exerciseListPresenter.onAttach(this);
        if (equipmentId!=null) {
            exerciseListPresenter.onViewPrepared(equipmentId);
        }
        else
        {
            exerciseListPresenter.onViewPrepared();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }
    @Override
    public void onFetchDataCompleted(ExerciseModel exerciseModel) {
        Log.i("OnFetchCalled", "passseddd");

        recyclerView.setAdapter(new ExerciseAdapter(exerciseModel, R.layout.exercise_list_item, getActivity().getApplicationContext(), new ExerciseRecyclerViewClickListener() {
            @Override
            public void onItemClick(ExerciseModel.Result item) {
                Integer cid=item.getId();
                Fragment fr=new ExerciseInfoFragment();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("CID", cid);
                Log.i("exercise id", cid.toString());
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
    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iAppComponent(((MyApplication) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
    }
}
