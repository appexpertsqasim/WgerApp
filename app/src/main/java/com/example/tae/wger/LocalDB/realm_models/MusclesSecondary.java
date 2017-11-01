package com.example.tae.wger.LocalDB.realm_models;

import io.realm.RealmObject;

/**
 * Created by TAE on 31/10/2017.
 */
public class MusclesSecondary  extends RealmObject {


    private Integer id;

    private String name;

    private Boolean isFront;

    public MusclesSecondary(Integer id, String name, Boolean isFront) {
        this.id = id;
        this.name = name;
        this.isFront = isFront;
    }

    public MusclesSecondary() {
    }

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

    public Boolean getIsFront() {
        return isFront;
    }

    public void setIsFront(Boolean isFront) {
        this.isFront = isFront;
    }

}
