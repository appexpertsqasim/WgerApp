package com.example.tae.wger.fragments;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.example.tae.wger.DI.component.DaggerIActivityComponent;
import com.example.tae.wger.DI.component.IActivityComponent;
import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.R;
import com.example.tae.wger.model.MuscleModel;
import com.example.tae.wger.svg.SvgDecoder;
import com.example.tae.wger.svg.SvgDrawableTranscoder;
import com.example.tae.wger.svg.SvgSoftwareLayerSetter;
import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.muscle.IMuscleListMvpView;
import com.example.tae.wger.ui.muscle.MuscleListPresenter;

import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.tae.wger.MyApplication.getApplication;

/**
 * Created by TAE on 25/10/2017.
 */

public class WorkoutMuscles extends BaseFragment implements IMuscleListMvpView {
    @Inject
    MuscleListPresenter<IMuscleListMvpView> muscleListPresenter;
    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }
    ArrayList<Integer> muscles;
    ArrayList<ImageView> images;
    ArrayList<ImageView> back_images;
    @BindView(R.id.front2)
    ImageView front2;
    @BindView(R.id.front1)
    ImageView front1;
    @BindView(R.id.front)
    ImageView front;
    @BindView(R.id.front3)
    ImageView front3;
    @BindView(R.id.front4)
    ImageView front4;
    @BindView(R.id.front5)
    ImageView front5;
    @BindView(R.id.front6)
    ImageView front6;
    @BindView(R.id.front7)
    ImageView front7;
    @BindView(R.id.front8)
    ImageView front8;
    @BindView(R.id.front9)
    ImageView front9;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.back1)
    ImageView back1;
    @BindView(R.id.back2)
    ImageView back2;
    @BindView(R.id.back3)
    ImageView back3;
    @BindView(R.id.back4)
    ImageView back4;
    @BindView(R.id.back5)
    ImageView back5;
    @BindView(R.id.back6)
    ImageView back6;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        muscles = getArguments().getIntegerArrayList("CID");
        Log.i("Size OnCreate",""+muscles.size());
        return inflater.inflate(R.layout.workout_muscles,container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initialiseDagger();
       // muscleListPresenter = new MuscleListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        muscleListPresenter.onAttach(this);
        muscleListPresenter.onViewPrepared();
        ButterKnife.bind(this, view);
        images=new ArrayList<ImageView>();
        images.add(front1);
        images.add(front2);
        images.add(front3);
        images.add(front4);
        images.add(front5);
        images.add(front6);
        images.add(front7);
        images.add(front8);
        images.add(front9);
        back_images=new ArrayList<ImageView>();
        back_images.add(back1);
        back_images.add(back2);
        back_images.add(back3);
        back_images.add(back4);
        back_images.add(back5);
        back_images.add(back6);
        super.onViewCreated(view, savedInstanceState);


    }

    public void svgImageLoader(MuscleModel model){
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

        Uri uri = Uri.parse("https://wger.de/static/images/muscles/muscular_system_front.svg");
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(front);

        Uri uri3 = Uri.parse("https://wger.de/static/images/muscles/muscular_system_back.svg");
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri3)
                .into(back);
        int svg=0;
        int svg2=0;
        for (int i=0; i < muscles.size(); i++){
           for (int j=0; j < model.getResults().size(); j++){
                if(!(model.getResults().get(j).getId().equals(muscles.get(i)))){
                    Log.i("Compare","No matches"+muscles.get(0).toString());
                }else{
                    //do something for equals

                    if (model.getResults().get(j).getIsFront()) {
                        Log.i("if statement","true");
                        Uri uri2 = Uri.parse("https://wger.de/static/images/muscles/main/muscle-" + model.getResults().get(j).getId() + ".svg");
                        requestBuilder
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                // SVG cannot be serialized so it's not worth to cache it
                                .load(uri2)
                                .into(images.get((svg)));
                        Log.i("front muscle id", String.valueOf(model.getResults().get(j).getId()));
                                svg++;

                    }else {

                        Uri uri4 = Uri.parse("https://wger.de/static/images/muscles/main/muscle-" + model.getResults().get(j).getId()+ ".svg");
                        requestBuilder
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                // SVG cannot be serialized so it's not worth to cache it
                                .load(uri4)
                                .into(back_images.get((svg)));
                        Log.i("back muscle id", String.valueOf(model.getResults().get(j).getId()));
                        svg2++;

                    }

                }
            }
        }

        }




    @Override
    public void onFetchDataCompleted(MuscleModel muscleModel) {
        svgImageLoader(muscleModel);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String message) {
     Log.i("error",message);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }
    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iAppComponent(((MyApplication) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
    }
}
