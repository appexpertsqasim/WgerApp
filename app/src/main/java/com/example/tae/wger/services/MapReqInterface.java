package com.example.tae.wger.services;

import com.example.tae.wger.model.GymMapModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TAE on 26/10/2017.
 */

public interface MapReqInterface {

    @GET(ApiConstants.QUERY)
    Observable<GymMapModel> getNearestGyms(@Query("location")
                                                   String location,@Query("type")
            String type, @Query("radius")
            String radius, @Query("key")  String param3);
}