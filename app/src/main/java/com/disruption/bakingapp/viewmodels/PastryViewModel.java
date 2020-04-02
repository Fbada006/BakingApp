package com.disruption.bakingapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.disruption.bakingapp.data.PastryRepository;
import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.utils.Resource;

import java.util.List;

public class PastryViewModel extends ViewModel {
    private LiveData<Resource<List<Pastry>>> mPastryResource;

    public PastryViewModel() {
        mPastryResource = PastryRepository.getPastries();
    }

    public LiveData<Resource<List<Pastry>>> getPastryResource() {
        return mPastryResource;
    }
}
