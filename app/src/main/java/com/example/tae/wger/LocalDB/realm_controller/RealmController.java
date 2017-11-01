package com.example.tae.wger.LocalDB.realm_controller;

import com.example.tae.wger.LocalDB.realm_models.RealmEquipmentModel;
import com.example.tae.wger.LocalDB.realm_models.RealmExerciseInfoModel;
import com.example.tae.wger.LocalDB.realm_models.RealmExerciseModel;

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
    public void saveExerciseInfo(RealmExerciseInfoModel realmExerciseInfoModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmExerciseInfoModel);
            }
        });
    }
    public RealmExerciseInfoModel getOneItem(int id){

        RealmExerciseInfoModel item = realm.where(RealmExerciseInfoModel.class).equalTo("id",id).findFirst();

        return item;
    }
    public ArrayList<RealmEquipmentModel> getEquipmentLists(){
        ArrayList<RealmEquipmentModel> realmEquipmentModelArraylist =new ArrayList<>();
        RealmResults<RealmEquipmentModel> realmResults=realm.where(RealmEquipmentModel.class).findAll();
        for (RealmEquipmentModel realmEquipmentModel : realmResults){
            realmEquipmentModelArraylist.add(realmEquipmentModel);
        }
        return realmEquipmentModelArraylist;
    }
    public void saveExercise(final RealmExerciseModel exerciseModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(exerciseModel);
            }
        });
    }

    public void deleteDatabase() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

    public ArrayList<RealmExerciseModel> getExerciseLists(){
        ArrayList<RealmExerciseModel> realmExerciseModelArraylist =new ArrayList<>();
        RealmResults<RealmExerciseModel> realmResults=realm.where(RealmExerciseModel.class).findAll();
        for (RealmExerciseModel realmExerciseModel : realmResults){
            realmExerciseModelArraylist.add(realmExerciseModel);
        }
        return realmExerciseModelArraylist;
    }

}
