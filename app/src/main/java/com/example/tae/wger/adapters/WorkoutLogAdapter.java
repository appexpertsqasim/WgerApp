package com.example.tae.wger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.R;
import com.example.tae.wger.listener.WorkoutLogRecyclerViewClickListener;
import com.example.tae.wger.model.WorkoutLogModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE on 19/10/2017.
 */

public class WorkoutLogAdapter extends RecyclerView.Adapter<WorkoutLogAdapter.workoutlogViewHolder>{
    WorkoutLogModel workout;

    //List<EquipmentModel> workout;
    int row;
    Context applicationContext;
    WorkoutLogRecyclerViewClickListener rListener;

    public WorkoutLogAdapter(WorkoutLogModel workoutLogModel, int row, Context applicationContext, WorkoutLogRecyclerViewClickListener listener) {
        this.workout = workoutLogModel;
        this.row = row;
        this.applicationContext = applicationContext;
        this.rListener=listener;
    }


    @Override
    public workoutlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, null);
        return new workoutlogViewHolder(view);

    }

    @Override
    public void onBindViewHolder(workoutlogViewHolder holder, int position) {
        holder.bind(workout.getResults().get(position), rListener);
        holder.name.setText(workout.getResults().get(position).getId()+"Exercise ID" +
                workout.getResults().get(position).getId());



    }
    @Override
    public int getItemCount() {
        return workout.getResults().size();
    }

    public class workoutlogViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.workout_tv)
        TextView name;
        public workoutlogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final WorkoutLogModel.Result result, final WorkoutLogRecyclerViewClickListener rListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    rListener.onItemClick(result);
                }
            });
        }
    }
}
