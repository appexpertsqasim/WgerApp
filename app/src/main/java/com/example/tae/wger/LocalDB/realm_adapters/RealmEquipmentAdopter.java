package com.example.tae.wger.LocalDB.realm_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.LocalDB.realm_models.RealmEquipmentModel;
import com.example.tae.wger.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE Consultant on 27/10/2017.
 */

public class RealmEquipmentAdopter extends RecyclerView.Adapter<RealmEquipmentAdopter.MyViewHolder> {
    /**
     *
     */
    ArrayList<RealmEquipmentModel> realmEquipment;
    int row;
    Context applicationContext;
    public RealmEquipmentAdopter(ArrayList<RealmEquipmentModel> realmModel, int row, Context applicationContext) {
        this.realmEquipment=realmModel;
        this.row=row;
        this.applicationContext=applicationContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=  LayoutInflater.from(parent.getContext()).inflate(row,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.equipment.setText(realmEquipment.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return realmEquipment.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipment_tv)
        TextView equipment;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
