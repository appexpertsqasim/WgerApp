package com.example.tae.wger.LocalDB.realm_models;

import io.realm.RealmObject;

/**
 * Created by TAE on 31/10/2017.
 */
public class Equipment extends RealmObject {


    private Integer id;

    private String name;

    public Equipment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Equipment() {
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

}
