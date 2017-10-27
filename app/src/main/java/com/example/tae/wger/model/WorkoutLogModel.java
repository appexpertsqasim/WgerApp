package com.example.tae.wger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TAE on 24/10/2017.
 */

public class WorkoutLogModel {
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("next")
        @Expose
        private Object next;
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

        public Object getNext() {
            return next;
        }

        public void setNext(Object next) {
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


    public class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("reps")
        @Expose
        private Integer reps;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("exercise")
        @Expose
        private Integer exercise;
        @SerializedName("workout")
        @Expose
        private Integer workout;
        @SerializedName("repetition_unit")
        @Expose
        private Integer repetitionUnit;
        @SerializedName("weight_unit")
        @Expose
        private Integer weightUnit;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getReps() {
            return reps;
        }

        public void setReps(Integer reps) {
            this.reps = reps;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getExercise() {
            return exercise;
        }

        public void setExercise(Integer exercise) {
            this.exercise = exercise;
        }

        public Integer getWorkout() {
            return workout;
        }

        public void setWorkout(Integer workout) {
            this.workout = workout;
        }

        public Integer getRepetitionUnit() {
            return repetitionUnit;
        }

        public void setRepetitionUnit(Integer repetitionUnit) {
            this.repetitionUnit = repetitionUnit;
        }

        public Integer getWeightUnit() {
            return weightUnit;
        }

        public void setWeightUnit(Integer weightUnit) {
            this.weightUnit = weightUnit;
        }

    }
}
