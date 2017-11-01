package com.example.tae.wger.LocalDB.realm_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.LocalDB.realm_models.RealmExerciseModel;
import com.example.tae.wger.R;
import com.example.tae.wger.listener.RealmExerciseRecyclerViewClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE Consultant on 27/10/2017.
 */

public class RealmExerciseAdopter extends RecyclerView.Adapter<RealmExerciseAdopter.MyViewHolder> {
    /**
     *
     */
    ArrayList<RealmExerciseModel> exercise;
    int row;
    Context applicationContext;
    RealmExerciseRecyclerViewClickListener rListener;
    public RealmExerciseAdopter(ArrayList<RealmExerciseModel> realmModel, int row, Context applicationContext, RealmExerciseRecyclerViewClickListener listener) {
        this.exercise = realmModel;
        this.row = row;
        this.applicationContext = applicationContext;
        this.rListener=listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=  LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(exercise.get(position), rListener);
        holder.name.setText(exercise.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return exercise.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.exercise_name)
        public TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind(final RealmExerciseModel result, final RealmExerciseRecyclerViewClickListener rListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    rListener.onItemClick(result);
                }
            });
        }
    }


}
