package com.example.tae.wger.LocalDB.realm_models;

import io.realm.RealmObject;

/**
 * Created by TAE Consultant on 27/10/2017.
 */

public class RealmEquipmentModel extends RealmObject {
    public RealmEquipmentModel(Integer id,String name) {

        this.name = name;
        this.id=id;
    }

    public RealmEquipmentModel() {
    }

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
