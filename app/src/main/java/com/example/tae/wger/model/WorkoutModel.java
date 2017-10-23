package com.example.tae.wger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TAE on 23/10/2017.
 */

public class WorkoutModel {

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
        @SerializedName("creation_date")
        @Expose
        private String creationDate;
        @SerializedName("comment")
        @Expose
        private String comment;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

    }
}
