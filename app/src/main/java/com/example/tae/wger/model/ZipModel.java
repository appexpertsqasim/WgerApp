package com.example.tae.wger.model;

/**
 * Created by TAE on 28/10/2017.
 */

public class ZipModel {
        public ZipModel(ExerciseModel exerciseModel, ExerciseImageModel exerciseImageModel) {
            this.model = exerciseModel;
            this.imageModel = exerciseImageModel;
        }

        public ExerciseImageModel imageModel;
        public ExerciseModel model;

}

