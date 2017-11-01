package com.example.tae.wger.fragments;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.tae.wger.DI.component.DaggerIActivityComponent;
import com.example.tae.wger.DI.component.IActivityComponent;
import com.example.tae.wger.DI.module.ActivityModule;
import com.example.tae.wger.LocalDB.realm_controller.RealmController;
import com.example.tae.wger.LocalDB.realm_models.Category;
import com.example.tae.wger.LocalDB.realm_models.Equipment;
import com.example.tae.wger.LocalDB.realm_models.Muscle;
import com.example.tae.wger.LocalDB.realm_models.MusclesSecondary;
import com.example.tae.wger.LocalDB.realm_models.RealmExerciseInfoModel;
import com.example.tae.wger.MyApplication;
import com.example.tae.wger.R;
import com.example.tae.wger.model.ExerciseInfoModel;
import com.example.tae.wger.svg.SvgDecoder;
import com.example.tae.wger.svg.SvgDrawableTranscoder;
import com.example.tae.wger.svg.SvgSoftwareLayerSetter;
import com.example.tae.wger.ui.base.BaseFragment;
import com.example.tae.wger.ui.exercise_info.ExerciseInfoListPresenter;
import com.example.tae.wger.ui.exercise_info.IExerciseInfoListMvpView;

import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

import static com.example.tae.wger.MyApplication.getApplication;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseInfoFragment extends BaseFragment implements IExerciseInfoListMvpView {
    @Inject
    ExerciseInfoListPresenter<IExerciseInfoListMvpView> exerciseInfoListPresenter;
    RealmController controller;
    Realm realm;
    RealmExerciseInfoModel realmExerciseInfoModel;
    Integer exerciseId;
    @BindView(R.id.category_tv)
    TextView category;
    @BindView(R.id.description_text)
    TextView description;
    @BindView(R.id.name_tv)
    TextView name;
    @BindView(R.id.equipment_text)
    TextView equipment;
    @BindView(R.id.svg_muscle_iv)
    ImageView svg_front_muscle;
    @BindView(R.id.svg_muscle)
    ImageView svg_front;
    @BindView(R.id.secondary_iv)
    ImageView svg_back;
    @BindView(R.id.seconday_muscle_iv)
    ImageView svg_back_muscle;
    IActivityComponent iActivityComponent;

    public IActivityComponent getiActivityComponent() {
        return iActivityComponent;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         exerciseId = getArguments().getInt("CID");
        Log.i("exercise2 id", exerciseId.toString());
        return inflater.inflate(R.layout.exercise_info_layout,container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initialiseDagger();
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        controller = new RealmController(realm);
        realmExerciseInfoModel = new RealmExerciseInfoModel();
        isNetworkConnected();
        // exerciseInfoListPresenter = new ExerciseInfoListPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        if (isNetworkConnected()){
            exerciseInfoListPresenter.onAttach(this);
        exerciseInfoListPresenter.onViewPrepared(exerciseId);
    }
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onFetchDataCompleted(ExerciseInfoModel.Result exerciseInfoModel) {
        realmExerciseInfoModel.setName(exerciseInfoModel.getName());
        category.setText(exerciseInfoModel.getCategory().getName());
        name.setText(exerciseInfoModel.getName());
        description.setText(exerciseInfoModel.getDescription());
        final int size = exerciseInfoModel.getEquipment().size();
        String s = "";
        Category category=new Category(exerciseInfoModel.getCategory().getId(),exerciseInfoModel.getCategory().getName());
        realmExerciseInfoModel.setCategory(category);
        Log.i("realm model", String.valueOf(realmExerciseInfoModel.getCategory().getName()));
        RealmList<Muscle> muscleList = new RealmList<>();
        for (ExerciseInfoModel.Muscle muscle:exerciseInfoModel.getMuscles()) {
            muscleList.add(new Muscle(muscle.getId(),muscle.getName(),muscle.getIsFront()));
        }
        realmExerciseInfoModel.setMuscles(muscleList);
        Log.i("realm model", String.valueOf(realmExerciseInfoModel.getMuscles().isEmpty()));
        RealmList<MusclesSecondary> secondaryMuscleList = new RealmList<>();
        for (ExerciseInfoModel.MusclesSecondary muscle:exerciseInfoModel.getMusclesSecondary()) {
            secondaryMuscleList.add(new MusclesSecondary(muscle.getId(),muscle.getName(),muscle.getIsFront()));
        }
        realmExerciseInfoModel.setMusclesSecondary(secondaryMuscleList);
        Log.i("realm model", String.valueOf(realmExerciseInfoModel.getMusclesSecondary().isEmpty()));
        for (int i = 0; i < size; i++)
        {

            s = s + " " + exerciseInfoModel.getEquipment().get(i).getName();

            RealmList<Equipment> list = new RealmList<>();
            for (ExerciseInfoModel.Equipment equipment:exerciseInfoModel.getEquipment()) {
                list.add(new Equipment(equipment.getId(),equipment.getName()));
            }
            realmExerciseInfoModel.setEquipment(list);
//            List<Equipment> list = new ArrayList<>(exerciseInfoModel.getEquipment().size());
//            realmExerciseInfoModel.setEquipment(exerciseInfoModel.getEquipment());
            Log.i("realm model", String.valueOf(realmExerciseInfoModel.getEquipment().isEmpty()));

        }

        equipment.setText(s);
        realmExerciseInfoModel.setId(exerciseId);
        realmExerciseInfoModel.setDescription(exerciseInfoModel.getDescription());
        controller.saveExerciseInfo(realmExerciseInfoModel);
        Log.i("Connection test", String.valueOf(realmExerciseInfoModel.getId()));
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

    public boolean isNetworkConnected() {


        // get Connectivity Manager to get network status
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i("Connection test","passed");
            return true; //we have a connection
        } else {
            realmExerciseInfoModel =controller.getOneItem(exerciseId);
            Log.i("get Exerinfo", String.valueOf(realmExerciseInfoModel.getId()));
                category.setText(realmExerciseInfoModel.getCategory().getName());
                name.setText(realmExerciseInfoModel.getName());
                description.setText(realmExerciseInfoModel.getDescription());
                final int size = realmExerciseInfoModel.getEquipment().size();
                String s = "";
                for (int i = 0; i < size; i++)
                {
                    s = s + " " + realmExerciseInfoModel.getEquipment().get(i).getName();
                }
                equipment.setText(s);

            svgRealmImageLoader(realmExerciseInfoModel);
           return false;
        }}
    private void initialiseDagger() {
        iActivityComponent = DaggerIActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .iAppComponent(((MyApplication) getApplication()).getiApplicationComponent())
                .build();

        getiActivityComponent().inject(this);
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
    public void svgRealmImageLoader(RealmExerciseInfoModel exerciseInfoModel){
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
    }}

