package com.example.tae.wger.LocalDB.realm_models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by TAE on 31/10/2017.
 */

public class RealmExerciseInfoModel extends RealmObject {

            private Integer id;
            private String name;

            private Category category;

            private String description;

            private RealmList<Muscle> muscles = null;

            private RealmList<MusclesSecondary> musclesSecondary = null;

            private RealmList<Equipment> equipment = null;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
            public Integer getId() {return id;}

            public void setId(Integer id) {
        this.id = id;
    }

            public Category getCategory() {
                return category;
            }

            public void setCategory(Category category) {
                this.category = category;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public RealmList<Muscle> getMuscles() {
                return muscles;
            }

            public void setMuscles(RealmList<Muscle> muscles) {
                this.muscles = muscles;
            }

            public RealmList<MusclesSecondary> getMusclesSecondary() {
                return musclesSecondary;
            }

            public void setMusclesSecondary(RealmList<MusclesSecondary> musclesSecondary) {
                this.musclesSecondary = musclesSecondary;
            }

            public RealmList<Equipment> getEquipment() {
                return equipment;
            }

            public void setEquipment(RealmList<Equipment> equipment) {
                this.equipment = equipment;
            }


    }
