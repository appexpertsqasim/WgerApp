package com.example.tae.wger.LocalDB.realm_controller;

import com.example.tae.wger.LocalDB.realm_models.RealmEquipmentModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by TAE Consultant on 27/10/2017.
 */

public class RealmController {
    Realm realm;

    public RealmController(Realm realm) {
        this.realm = realm;
    }

    public void saveEquipment(final RealmEquipmentModel equipmentModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(equipmentModel);
            }
        });
    }
    public ArrayList<RealmEquipmentModel> getEquipmentLists(){
        ArrayList<RealmEquipmentModel> realmEquipmentModelArraylist =new ArrayList<>();
        RealmResults<RealmEquipmentModel> realmResults=realm.where(RealmEquipmentModel.class).findAll();
        for (RealmEquipmentModel realmEquipmentModel : realmResults){
            realmEquipmentModelArraylist.add(realmEquipmentModel);
        }
        return realmEquipmentModelArraylist;
    }
}
