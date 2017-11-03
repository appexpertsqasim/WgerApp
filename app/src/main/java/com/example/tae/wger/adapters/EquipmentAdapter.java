package com.example.tae.wger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.R;
import com.example.tae.wger.listener.EquipmentRecyclerViewClickListener;
import com.example.tae.wger.model.EquipmentModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE on 19/10/2017.
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.equipmentViewHolder>{
    EquipmentModel equipment;

    //List<EquipmentModel> workout;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(row, parent,false);
        return new equipmentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(equipmentViewHolder holder, int position) {
        holder.bind(equipment.getResults().get(position), rListener);
        holder.name.setText(equipment.getResults().get(position).getName());
//        holder.image.setImageDrawable(GetImage(applicationContext,equipment.getResults().get(position).getName().toLowerCase()));
//        Log.i("Model teset",equipment.getResults().get(position).getName());
//
//

    }
    @Override
    public int getItemCount() {
        return equipment.getResults().size();
    }
//    public static Drawable GetImage(Context c, String ImageName) {
//        if (c.getResources() != null) {
//            return c.getResources().getDrawable(c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName()));
//        } else {
//           return c.getResources().getDrawable(c.getResources().getIdentifier("dumbell_image", "drawable", c.getPackageName()));
//        }
//    }

    public class equipmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.equipment_tv)
        TextView name;

        public equipmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
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
