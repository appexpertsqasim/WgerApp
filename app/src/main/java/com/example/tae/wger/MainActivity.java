package com.example.tae.wger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.example.tae.wger.ResideMenu.ResideMenu;
import com.example.tae.wger.ResideMenu.ResideMenuItem;
import com.example.tae.wger.fragments.EquipmentFragment;
import com.example.tae.wger.fragments.ExerciseFragment;
import com.example.tae.wger.fragments.MuscleFragment;
import com.example.tae.wger.fragments.VideoFragment;
import com.example.tae.wger.fragments.WorkoutFragment;
import com.example.tae.wger.maps.NearestGyms;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemEquipment;
    private ResideMenuItem itemExercise;
    private ResideMenuItem itemWorkout;
    private ResideMenuItem itemMuscle;
    private ResideMenuItem itemGym;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new VideoFragment());

    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.drawable.background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.ic_home_black_24dp, R.string.home);
        itemEquipment  = new ResideMenuItem(this, R.drawable.ic_fitness_center_black_24dp,  R.string.equipment);
        itemExercise = new ResideMenuItem(this, R.drawable.exercise2, R.string.exercises);
        itemWorkout = new ResideMenuItem(this, R.drawable.workout, R.string.workout);
        itemMuscle = new ResideMenuItem(this, R.drawable.muscles, "Muscles");
        itemGym = new ResideMenuItem(this, R.drawable.ic_location_on_black_24dp, "Nearest Gyms");

        itemHome.setOnClickListener(this);
        itemEquipment.setOnClickListener(this);
        itemExercise.setOnClickListener(this);
        itemMuscle.setOnClickListener(this);
        itemWorkout.setOnClickListener(this);
        itemGym.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemEquipment, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemWorkout, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemExercise, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemMuscle, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemGym, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new VideoFragment());
        }else if (view == itemExercise){
            changeFragment(new ExerciseFragment());
        }else if (view == itemEquipment){
            changeFragment(new EquipmentFragment());
        }else if (view == itemWorkout){
            changeFragment(new WorkoutFragment());
        }
        else if (view == itemMuscle){
            changeFragment(new MuscleFragment());
        }
        else if (view == itemGym){
            Intent intent=new Intent(MainActivity.this,NearestGyms.class);
            startActivity(intent);
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack("")
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
}
