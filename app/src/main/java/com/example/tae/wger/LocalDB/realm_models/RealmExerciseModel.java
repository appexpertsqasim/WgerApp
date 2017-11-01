package com.example.tae.wger.LocalDB.realm_models;

import io.realm.RealmObject;

/**
 * Created by TAE Consultant on 31/10/2017.
 */

public class RealmExerciseModel extends RealmObject {
    public RealmExerciseModel(int id, String name,int equipment) {

        this.name = name;
        this.id=id;
    this.equipment=equipment;
    }

    public RealmExerciseModel() {
    }

    private int id;
    private String name;
    private int equipment;
    public int getId() {
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
    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

}
