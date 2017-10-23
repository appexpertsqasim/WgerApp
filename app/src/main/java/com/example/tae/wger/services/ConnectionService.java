package com.example.tae.wger.services;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by TheAppExperts on 19/10/2017.
 */

    public class ConnectionService {


    private static reqInterface varReqInter;
    static Retrofit retrofit;


        public static reqInterface getConnectionService(){

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(reqInterface.class);
        }


   }



