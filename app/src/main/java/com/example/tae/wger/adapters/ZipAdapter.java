package com.example.tae.wger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tae.wger.R;
import com.example.tae.wger.listener.ExerciseRecyclerViewClickListener;
import com.example.tae.wger.model.ExerciseModel;
import com.example.tae.wger.model.ZipModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE on 19/10/2017.
 */

public class ZipAdapter extends RecyclerView.Adapter<ZipAdapter.zipViewHolder>{
    ZipModel zipModel;

    //List<EquipmentModel> workout;
    int row;
    Context applicationContext;
    ExerciseRecyclerViewClickListener rListener;

    public ZipAdapter(ZipModel model, int row, Context applicationContext, ExerciseRecyclerViewClickListener listener) {
        this.zipModel = model;
        this.row = row;
        this.applicationContext = applicationContext;
        this.rListener=listener;
        Log.i("adapter zip", "callled");
    }


    @Override
    public zipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, parent,false);

        return new zipViewHolder(view);

    }

    @Override
    public void onBindViewHolder(zipViewHolder holder, int position) {
        Log.i("adapter", "called");
        holder.bind(zipModel.model.getResults().get(position), rListener);
        holder.name.setText(zipModel.model.getResults().get(position).getName());

        int exercise = zipModel.model.getResults().get(position).getId();
        Log.i("id", String.valueOf(exercise));
            for (int j = 0; j < zipModel.imageModel.getResults().size(); j++) {
                Log.i("J",String.valueOf(j));
                Integer exerciseId=zipModel.imageModel.getResults().get(j).getExercise();//exercise id in images api

                Log.i("exerciseI",String.valueOf(exerciseId));

                if(exerciseId==null){
                    Log.i("id","null");
                }
                else{
                if (!(exerciseId.equals(exercise))) {

                } else {
                    //do something for equals
                    Log.i("id1 "+exercise, "id2 " + exerciseId);
                    Picasso.with(applicationContext)
                            .load(zipModel.imageModel.getResults().get(j).getImage())
                            .resize(120, 120)
                            .into(holder.image);

                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return zipModel.model.getResults().size();
    }

    public class zipViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.exercise_name)
        TextView name;
        @BindView(R.id.exercise_iv)
        ImageView image;
        public zipViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final ExerciseModel.Result result, final ExerciseRecyclerViewClickListener rListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    rListener.onItemClick(result);
                }
            });
        }
    }
}
