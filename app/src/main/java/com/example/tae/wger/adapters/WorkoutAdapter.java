package com.example.tae.wger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.R;
import com.example.tae.wger.listener.WorkoutRecyclerViewClickListener;
import com.example.tae.wger.model.WorkoutModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE on 19/10/2017.
 */

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.workoutViewHolder>{
    WorkoutModel workout;

    //List<EquipmentModel> workout;
    int row;
    Context applicationContext;
    WorkoutRecyclerViewClickListener rListener;

    public WorkoutAdapter(WorkoutModel workoutModel, int row, Context applicationContext, WorkoutRecyclerViewClickListener listener) {
        this.workout = workoutModel;
        this.row = row;
        this.applicationContext = applicationContext;
        this.rListener=listener;
    }


    @Override
    public workoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, parent,false);
        return new workoutViewHolder(view);

    }

    @Override
    public void onBindViewHolder(workoutViewHolder holder, int position) {
        holder.bind(workout.getResults().get(position), rListener);
        holder.name.setText(workout.getResults().get(position).getId()+"--" +workout.getResults().get(position).getCreationDate());



    }
    @Override
    public int getItemCount() {
        return workout.getResults().size();
    }

    public class workoutViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.workout_tv)
        TextView name;
        public workoutViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final WorkoutModel.Result result, final WorkoutRecyclerViewClickListener rListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    rListener.onItemClick(result);
                }
            });
        }
    }
}
