package com.example.tae.wger.LocalDB.realm_models;

import io.realm.RealmObject;

/**
 * Created by TAE Consultant on 27/10/2017.
 */

public class RealmEquipmentModel extends RealmObject {
    public RealmEquipmentModel(String name) {

        this.name = name;
    }

    public RealmEquipmentModel() {
    }


    String name;
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;

    }
}
