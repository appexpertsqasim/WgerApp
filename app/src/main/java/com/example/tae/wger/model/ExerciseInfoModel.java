package com.example.tae.wger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TAE on 19/10/2017.
 */

public class ExerciseInfoModel {

        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("next")
        @Expose
        private String next;
        @SerializedName("previous")
        @Expose
        private Object previous;
        @SerializedName("results")
        @Expose
        private List<Result> results = null;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public Object getPrevious() {
            return previous;
        }

        public void setPrevious(Object previous) {
            this.previous = previous;
        }

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }


    public class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
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


    public class Equipment {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
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




    public class Muscle {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("is_front")
        @Expose
        private Boolean isFront;

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


    public class MusclesSecondary {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("is_front")
        @Expose
        private Boolean isFront;

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

    public class Result {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("category")
        @Expose
        private Category category;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("muscles")
        @Expose
        private List<Muscle> muscles = null;
        @SerializedName("muscles_secondary")
        @Expose
        private List<MusclesSecondary> musclesSecondary = null;
        @SerializedName("equipment")
        @Expose
        private List<Equipment> equipment = null;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public List<Muscle> getMuscles() {
            return muscles;
        }

        public void setMuscles(List<Muscle> muscles) {
            this.muscles = muscles;
        }

        public List<MusclesSecondary> getMusclesSecondary() {
            return musclesSecondary;
        }

        public void setMusclesSecondary(List<MusclesSecondary> musclesSecondary) {
            this.musclesSecondary = musclesSecondary;
        }

        public List<Equipment> getEquipment() {
            return equipment;
        }

        public void setEquipment(List<Equipment> equipment) {
            this.equipment = equipment;
        }

    }
}
