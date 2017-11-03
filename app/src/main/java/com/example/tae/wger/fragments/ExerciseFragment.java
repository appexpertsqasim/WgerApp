package com.example.tae.wger.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Button;

import com.example.tae.wger.DI.component.DaggerIActivityComponent;
import com.example.tae.wger.DI.component.IActivityComponent;
import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.LocalDB.realm_adapters.RealmExerciseAdopter;
import com.example.tae.wger.LocalDB.realm_controller.RealmController;
import com.example.tae.wger.LocalDB.realm_models.RealmExerciseModel;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.R;
import com.example.tae.wger.adapters.ExerciseAdapter;
import com.example.tae.wger.listener.ExerciseRecyclerViewClickListener;
import com.example.tae.wger.listener.RealmExerciseRecyclerViewClickListener;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.exercise.ExerciseListPresenter;
import com.example.tae.wger.ui.exercise.IExerciseListMvpView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.tae.wger.MyApplication.getApplication;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseFragment extends BaseFragment implements IExerciseListMvpView {
    @Inject
    ExerciseListPresenter<IExerciseListMvpView> exerciseListPresenter;
    IActivityComponent iActivityComponent;
    ExerciseAdapter exerciseAdapter;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    RealmController controller;
    Realm realm;
    RealmExerciseModel realmExerciseModel;
    @BindView(R.id.next_btn)
    Button next;
    @BindView(R.id.back_btn)
    Button back;
    Integer equipmentId;
    String ePage;
    Integer count;
    String ePageBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if( getArguments()!=null){
            equipmentId = getArguments().getInt("CID");
        }

        return inflater.inflate(R.layout.exercise_recycler_view_layout,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initialiseDagger();
        realm=Realm.getDefaultInstance();
        controller=new RealmController(realm);
        realmExerciseModel =new RealmExerciseModel();
        isNetworkConnected();
        count=1;
       // exerciseListPresenter = new ExerciseListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        exerciseListPresenter.onAttach(this);
        ArrayList<RealmExerciseModel> filtered=new ArrayList<>();
        if (equipmentId!=null&&isNetworkConnected()) {
            Log.i("Check","Internet and no id");
            exerciseListPresenter.onViewPrepared(equipmentId);}
        else if(!isNetworkConnected()&&equipmentId!=null) {
            Log.i("Check", "no Internet and id");
            for (int i = 0; i < controller.getExerciseLists().size(); i++) {
                if (controller.getExerciseLists().get(i).getEquipment() == equipmentId) {
                    filtered.add(controller.getExerciseLists().get(i));

                }

            }
            recyclerView.setAdapter(new RealmExerciseAdopter(filtered, R.layout.exercise_list_item, getActivity().getApplicationContext(), new RealmExerciseRecyclerViewClickListener() {
                @Override
                public void onItemClick(RealmExerciseModel item) {
                    Integer cid = item.getId();
                    Fragment fr = new ExerciseInfoFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Bundle args = new Bundle();
                    args.putInt("CID", cid);
                    fr.setArguments(args);
                    ft.replace(R.id.container, fr);
                    ft.addToBackStack("");
                    ft.commit();
                }

            }));
        }
        else
        {
            Log.i("Check","Internet and no id");
            exerciseListPresenter.onViewPrepared();

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=count+1;
                 String s1=String.valueOf(count);

                    exerciseListPresenter.onViewPreparedPage(s1);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=count-1;
                String s1=String.valueOf(count);
                if(s1.equals("/")){
                    exerciseListPresenter.onViewPrepared();
                }else
                {
                    exerciseListPresenter.onViewPreparedPage(s1);
                }

            }
        });
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onFetchDataCompleted(ExerciseModel exerciseModel) {
        Log.i("fetch data completed","test");
        for(ExerciseModel.Result result: exerciseModel.getResults()) {
            Log.i("fetch data completed", String.valueOf(result.getId()));
            if (result.getEquipment()!=null && result.getEquipment().size()>0){
                realmExerciseModel.setEquipment(result.getEquipment().get(0));
            }
           realmExerciseModel.setName(result.getName());
            realmExerciseModel.setId(result.getId());
            Integer id = result.getId();
            Log.i("realm exercise model", String.valueOf(realmExerciseModel.getId()));
             controller.saveExercise(realmExerciseModel);

        }
        exerciseAdapter=new ExerciseAdapter(exerciseModel, R.layout.exercise_list_item, getActivity().getApplicationContext(), new ExerciseRecyclerViewClickListener() {
            @Override
            public void onItemClick(ExerciseModel.Result item) {
                Integer cid = item.getId();
                Fragment fr = new ExerciseInfoFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("CID", cid);
                fr.setArguments(args);
                ft.replace(R.id.container, fr);
                ft.addToBackStack("");
                ft.commit();

            }
        });
        recyclerView.setAdapter(exerciseAdapter);
        Log.i("1st Page","next check");
        ePage =exerciseModel.getNext();
        if(exerciseModel.getPrevious()==null)
        {
            Log.i("Next Page inside if","back");
            back.setVisibility(View.INVISIBLE);

        }
        else
        {
            ePageBack=exerciseModel.getPrevious().toString();
            back.setVisibility(View.VISIBLE);
        }
        if(ePage==null) {
            Log.i("Next Page inside if","next");
            next.setVisibility(View.INVISIBLE);
        }else
        {
            next.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onFetchDataCompletedNext(ExerciseModel exerciseModel) {

        exerciseAdapter=new ExerciseAdapter(exerciseModel, R.layout.exercise_list_item, getActivity().getApplicationContext(), new ExerciseRecyclerViewClickListener() {
            @Override
            public void onItemClick(ExerciseModel.Result item) {
                Integer cid = item.getId();
                Fragment fr = new ExerciseInfoFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("CID", cid);
                fr.setArguments(args);
                ft.replace(R.id.container, fr);
                ft.addToBackStack("");
                ft.commit();

            }
        });
        recyclerView.setAdapter(exerciseAdapter);
        Log.i("1st Page","next check");
        ePage =exerciseModel.getNext();
        if(exerciseModel.getPrevious()==null)
        {
            Log.i("Next Page inside if","back");
            back.setVisibility(View.INVISIBLE);

        }
        else
        {

            ePageBack=exerciseModel.getPrevious().toString();
            back.setVisibility(View.VISIBLE);
        }
        if(ePage==null) {
            Log.i("Next Page inside if","next");
            next.setVisibility(View.INVISIBLE);
        } else
    {
        next.setVisibility(View.VISIBLE);
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
    public boolean isNetworkConnected() {


        // get Connectivity Manager to get network status
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i("Connection test","passed");
            return true; //we have a connection
        } else {
            recyclerView.setAdapter(new RealmExerciseAdopter(controller.getExerciseLists(), R.layout.exercise_list_item, getActivity().getApplicationContext(), new RealmExerciseRecyclerViewClickListener() {
                @Override
                public void onItemClick(RealmExerciseModel item) {
                    Integer cid = item.getId();
                    Fragment fr = new ExerciseInfoFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Bundle args = new Bundle();
                    args.putInt("CID", cid);
                    fr.setArguments(args);
                    ft.replace(R.id.container, fr);
                    ft.addToBackStack("");
                    ft.commit();


                }
            }));
            return false;
        }}
    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iAppComponent(((MyApplication) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
    }
}
