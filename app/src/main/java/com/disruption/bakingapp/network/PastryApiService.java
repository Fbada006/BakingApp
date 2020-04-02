package com.disruption.bakingapp.network;

import com.disruption.bakingapp.model.Pastry;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface PastryApiService {

    @GET("baking.json")
    Flowable<List<Pastry>> getPastries();
}
