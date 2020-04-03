package com.disruption.bakingapp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.disruption.bakingapp.utils.Constants.BASE_URL;

public class PastryApiServiceProvider {
    private static Retrofit retrofit;

    private static Retrofit retrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PastryApiService getPastryApiService() {
        return retrofitInstance().create(PastryApiService.class);
    }
}
