package com.example.tae.wger.ui.equipment;


import com.example.tae.wger.model.EquipmentModel;
import com.example.tae.wger.ui.base.MvpView;

/**
 * Created by TAE on 29/09/2017.
 */

public interface IEquipmentListMvpView extends MvpView {
    void onFetchDataCompleted(EquipmentModel equipmentModel);
}
