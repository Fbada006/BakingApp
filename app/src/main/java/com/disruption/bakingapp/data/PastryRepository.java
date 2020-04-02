package com.disruption.bakingapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.network.PastryApiServiceProvider;
import com.disruption.bakingapp.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class PastryRepository {
    private static MediatorLiveData<Resource<List<Pastry>>> mPastryResource;

    public static LiveData<Resource<List<Pastry>>> getPastries() {
        if (mPastryResource == null) {
            mPastryResource = new MediatorLiveData<>();
            mPastryResource.setValue(Resource.loading());

            final LiveData<Resource<List<Pastry>>> source =
                    LiveDataReactiveStreams.fromPublisher(
                            PastryApiServiceProvider.getPastryApiService().getPastries()
                                    .onErrorReturn(throwable -> {
                                        Pastry pastry = new Pastry();
                                        pastry.setId(-111111111);
                                        pastry.setErrorMessage(throwable.getMessage());
                                        List<Pastry> pastries = new ArrayList<>();
                                        pastries.add(pastry);
                                        return pastries;
                                    })
                                    .map(response -> {
                                        if (response.size() > 0) {
                                            if (response.get(0).getId() == -111111111) {
                                                return Resource.error(response.get(0).getErrorMessage(), response);
                                            }
                                        }
                                        return Resource.success(response);
                                    })
                                    .subscribeOn(Schedulers.io())
                    );
            mPastryResource.addSource(source, listResource -> {
                mPastryResource.setValue(listResource);
                mPastryResource.removeSource(source);
            });
        }

        return mPastryResource;
    }
}
