package com.example.tae.wger.fragments;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.example.tae.wger.R;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.network.AppDataManager;
import com.example.tae.wger.svg.SvgDecoder;
import com.example.tae.wger.svg.SvgDrawableTranscoder;
import com.example.tae.wger.svg.SvgSoftwareLayerSetter;
import com.example.tae.wger.ui.exercise_info.ExerciseInfoListPresenter;
import com.example.tae.wger.ui.exercise_info.IExerciseInfoListMvpView;
import com.example.tae.wger.ui.utils.rx.AppSchedulerProvider;

import java.io.InputStream;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseInfoFragment extends Fragment implements IExerciseInfoListMvpView {
    ExerciseInfoListPresenter<IExerciseInfoListMvpView> exerciseInfoListPresenter;
    Integer exerciseId;
    TextView category,description,name,equipment;
    StringBuilder stringBuilder;
    ImageView svg_front_muscle,svg_front,svg_back,svg_back_muscle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         exerciseId = getArguments().getInt("CID");
        Log.i("exercise id", exerciseId.toString());
        return inflater.inflate(R.layout.exercise_info_layout,container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exerciseInfoListPresenter = new ExerciseInfoListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        exerciseInfoListPresenter.onAttach(this);
        exerciseInfoListPresenter.onViewPrepared(exerciseId);
        category=(TextView)view.findViewById(R.id.category_tv);
        name=(TextView)view.findViewById(R.id.name_tv);
        description=(TextView)view.findViewById(R.id.description_text);
        equipment=(TextView)view.findViewById(R.id.equipment_text);
        svg_front =(ImageView)view.findViewById(R.id.svg_muscle);
       svg_front_muscle =(ImageView)view.findViewById(R.id.svg_muscle_iv);
       svg_back =(ImageView)view.findViewById(R.id.secondary_iv);
        svg_back_muscle =(ImageView)view.findViewById(R.id.seconday_muscle_iv);




    }
    @Override
    public void onFetchDataCompleted(ExerciseInfoModel.Result exerciseInfoModel) {
        category.setText(exerciseInfoModel.getCategory().getName());
        name.setText(exerciseInfoModel.getName());
        description.setText(exerciseInfoModel.getDescription());
        final int size = exerciseInfoModel.getEquipment().size();
        String s = "";
        for (int i = 0; i < size; i++)
        {
            Log.i("error", "No Equipments");
            s = s + " " + exerciseInfoModel.getEquipment().get(i).getName();


        }

        equipment.setText(s);
        svgImageLoader(exerciseInfoModel);
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

    public void svgImageLoader(ExerciseInfoModel.Result exerciseInfoModel){
        GenericRequestBuilder requestBuilder = Glide.with(getActivity())
                .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());
        if (exerciseInfoModel.getMuscles().get(0).getIsFront()) {
            Log.i("if statement","true");
            Uri uri = Uri.parse("https://wger.de/static/images/muscles/muscular_system_front.svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri)
                    .into(svg_front);
            Uri uri2 = Uri.parse("https://wger.de/static/images/muscles/main/muscle-" + exerciseInfoModel.getMuscles().get(0).getId() + ".svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri2)
                    .into(svg_front_muscle);

        }else {
            Log.i("if statement","false");
            Uri uri3 = Uri.parse("https://wger.de/static/images/muscles/muscular_system_back.svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri3)
                    .into(svg_front);
            Uri uri4 = Uri.parse("https://wger.de/static/images/muscles/main/muscle-" + exerciseInfoModel.getMuscles().get(0).getId() + ".svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri4)
                    .into(svg_front_muscle);
        }

        if(exerciseInfoModel.getMusclesSecondary().get(0).getIsFront()){
            Log.i("2nd if statement","true");
            Uri uri3 = Uri.parse("https://wger.de/static/images/muscles/muscular_system_back.svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri3)
                    .into(svg_back);
            Uri uri4 = Uri.parse("https://wger.de/static/images/muscles/secondary/muscle-" + exerciseInfoModel.getMusclesSecondary().get(0).getId() + ".svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri4)
                    .into(svg_back_muscle);
        }
        else{
            Log.i("2nd if statement","false");
            Uri uri3 = Uri.parse("https://wger.de/static/images/muscles/muscular_system_back.svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri3)
                    .into(svg_back);
            Uri uri4 = Uri.parse("https://wger.de/static/images/muscles/secondary/muscle-" + exerciseInfoModel.getMusclesSecondary().get(0).getId() + ".svg");
            requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    // SVG cannot be serialized so it's not worth to cache it
                    .load(uri4)
                    .into(svg_back_muscle);
        }
    }
}
