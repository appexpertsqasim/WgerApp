package com.example.tae.wger.ui.gyms;


import com.example.tae.wger.model.GymMapModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 26/10/2017.
 */

public interface IGymMvpView extends MvpView {
    void onFetchDataCompleted(GymMapModel gymMapModel);
}
