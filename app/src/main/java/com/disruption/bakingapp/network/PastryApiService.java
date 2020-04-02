package com.disruption.bakingapp.network;

import com.disruption.bakingapp.model.PastryResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface PastryApiService {

    @GET("baking.json")
    Flowable<PastryResponse> getPastries();
}
