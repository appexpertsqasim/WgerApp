package com.example.tae.wger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.R;
import com.example.tae.wger.listener.EquipmentRecyclerViewClickListener;
import com.example.tae.wger.model.EquipmentModel;

/**
 * Created by TAE on 19/10/2017.
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.equipmentViewHolder>{
    EquipmentModel equipment;

    //List<EquipmentModel> equipment;
    int row;
    Context applicationContext;
    EquipmentRecyclerViewClickListener rListener;

    public EquipmentAdapter(EquipmentModel equipment, int row, Context applicationContext, EquipmentRecyclerViewClickListener listener) {
        this.equipment = equipment;
        this.row = row;
        this.applicationContext = applicationContext;
        this.rListener=listener;
    }


    @Override
    public equipmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, null);
        return new equipmentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(equipmentViewHolder holder, int position) {
        holder.bind(equipment.getResults().get(position), rListener);
        holder.name.setText(equipment.getResults().get(position).getName());
        Log.i("Model teset",equipment.getResults().get(position).getName());



    }
    @Override
    public int getItemCount() {
        return equipment.getResults().size();
    }

    public class equipmentViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public equipmentViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.subtitle);
        }

        public void bind(final EquipmentModel.Result result, final EquipmentRecyclerViewClickListener rListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    rListener.onItemClick(result);
                }
            });
        }
    }
}
