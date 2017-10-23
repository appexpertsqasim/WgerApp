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

import com.example.tae.wger.R;
import com.example.tae.wger.adapters.EquipmentAdapter;
import com.example.tae.wger.listener.EquipmentRecyclerViewClickListener;
import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.network.AppDataManager;
import com.example.tae.wger.ui.equipment.EquipmentListPresenter;
import com.example.tae.wger.ui.equipment.IEquipmentListMvpView;
import com.example.tae.wger.ui.utils.rx.AppSchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TAE on 19/10/2017.
 */

public class EquipmentFragment extends Fragment implements IEquipmentListMvpView {
    EquipmentListPresenter<IEquipmentListMvpView> equipmentListPresenter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.recycler_view_layout,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        equipmentListPresenter = new EquipmentListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        equipmentListPresenter.onAttach(this);
        equipmentListPresenter.onViewPrepared();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);


    }
    @Override
    public void onFetchDataCompleted(EquipmentModel equipmentModel) {
        Log.i("OnFetchCalled", "passseddd");

        recyclerView.setAdapter(new EquipmentAdapter(equipmentModel, R.layout.equipment_list_item, getActivity().getApplicationContext(), new EquipmentRecyclerViewClickListener() {
            @Override
            public void onItemClick(EquipmentModel.Result item) {
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
