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

import com.example.tae.wger.DI.component.DaggerIActivityComponent;
import com.example.tae.wger.DI.component.IActivityComponent;
import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.LocalDB.realm_adapters.RealmEquipmentAdopter;
import com.example.tae.wger.LocalDB.realm_controller.RealmController;
import com.example.tae.wger.LocalDB.realm_models.RealmEquipmentModel;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.R;
import com.example.tae.wger.adapters.EquipmentAdapter;
import com.example.tae.wger.listener.EquipmentRecyclerViewClickListener;
import com.example.tae.wger.listener.RealmEquipmentRecyclerViewClickListener;
import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.equipment.EquipmentListPresenter;
import com.example.tae.wger.ui.equipment.IEquipmentListMvpView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.example.tae.wger.MyApplication.getApplication;


/**
 * Created by TAE on 19/10/2017.
 */

public class EquipmentFragment extends BaseFragment implements IEquipmentListMvpView {
    @Inject
    EquipmentListPresenter<IEquipmentListMvpView> equipmentListPresenter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    IActivityComponent iActivityComponent;
    Realm realm;
    RealmController controller;
    RealmEquipmentModel realmEquipmentModel;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.equipment_recycler_view_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        ButterKnife.bind(this,view);
        Realm.init(getActivity().getApplicationContext());
        realm=Realm.getDefaultInstance();
        controller=new RealmController(realm);
        initialiseDagger();
        isNetworkConnected();
//        equipmentListPresenter = new GymPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        equipmentListPresenter.onAttach(this);
        equipmentListPresenter.onViewPrepared();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onFetchDataCompleted(EquipmentModel equipmentModel) {
        Log.i("OnFetchCalled", "passseddd");
        for(EquipmentModel.Result result: equipmentModel.getResults()) {
            String name = result.getName();
            Integer id = result.getId();
            realmEquipmentModel =new RealmEquipmentModel();
            realmEquipmentModel.setName(name);
            realmEquipmentModel.setId(id);
            controller.saveEquipment(realmEquipmentModel);
            Log.i("Testing realm",realmEquipmentModel.getName());
        }
        recyclerView.setAdapter(new EquipmentAdapter(equipmentModel, R.layout.equipment_list_item, getActivity().getApplicationContext(), new EquipmentRecyclerViewClickListener() {
            @Override
            public void onItemClick(EquipmentModel.Result item) {
                Integer cid = item.getId();
                Fragment fr = new ExerciseFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("CID", cid);
                fr.setArguments(args);
                ft.replace(R.id.container, fr);
                ft.commit();
                ft.addToBackStack("");

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
        Log.i("errrrrorr", message);
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
            recyclerView.setAdapter(new RealmEquipmentAdopter(controller.getEquipmentLists(), R.layout.equipment_list_item, getActivity().getApplicationContext(), new RealmEquipmentRecyclerViewClickListener() {
                @Override
                public void onItemClick(RealmEquipmentModel item) {
                    Integer cid = item.getId();
                    Fragment fr = new ExerciseFragment();
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