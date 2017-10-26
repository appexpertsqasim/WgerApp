package com.example.tae.wger.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tae.wger.R;
import com.example.tae.wger.listener.MuscleRecyclerViewClickListener;
import com.example.tae.wger.model.MuscleModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TAE on 19/10/2017.
 */

public class MuscleAdapter extends RecyclerView.Adapter<MuscleAdapter.muscleViewHolder>{
     MuscleModel muscleModel;

    //List<EquipmentModel> workout;
    int row;
    Context applicationContext;
    MuscleRecyclerViewClickListener rListener;

    public MuscleAdapter(MuscleModel muscle, int row, Context applicationContext, MuscleRecyclerViewClickListener listener) {
        this.muscleModel = muscle;
        this.row = row;
        this.applicationContext = applicationContext;
        this.rListener=listener;
        Log.i("adapter", "callled");
    }


    @Override
    public muscleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row, null);
        Log.i("view Holder","passseddd");
        return new muscleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(muscleViewHolder holder, int position) {
        holder.bind(muscleModel.getResults().get(position), rListener);
        holder.name.setText(muscleModel.getResults().get(position).getName());
        Log.i("nameeeeeeeeee",muscleModel.getResults().get(position).getName());
//        Picasso.with(applicationContext)
//                .load("https://wger.de/media/exercise-images/"+exerciseModel.getResults().get(position).getId()+"/"+exerciseModel.getResults().get(position).getName()+".png")
//                .resize(500, 500)
//                .centerCrop()
//                .into(holder.imgExercise);
//        Log.i("url","https://wger.de/media/exercise-images/"+exerciseModel.getResults().get(position).getId()+"/"+exerciseModel.getResults().get(position).getName()+".png");
 }
    @Override
    public int getItemCount() {
        return muscleModel.getResults().size();
    }

    public class muscleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.muscle_name)
        TextView name;
        public muscleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final MuscleModel.Result result, final MuscleRecyclerViewClickListener rListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    rListener.onItemClick(result);
                }
            });
        }
    }
}
