package com.disruption.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.disruption.bakingapp.adapters.PastryAdapter;
import com.disruption.bakingapp.data.TinyDb;
import com.disruption.bakingapp.databinding.ActivityMainBinding;
import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.utils.Constants;
import com.disruption.bakingapp.viewmodels.PastryViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private PastryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(PastryViewModel.class);

        observeViewModelForPastries();
    }

    private void observeViewModelForPastries() {
        PastryAdapter adapter = new PastryAdapter(this::onPastryClick);
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        mViewModel.getPastryResource().observe(this, pastryResource -> {
            switch (pastryResource.status) {
                case SUCCESS:
                    if (pastryResource.data != null && !pastryResource.data.isEmpty()) {
                        new TinyDb(this).saveListOfPastries(Constants.PASTRY_LIST_KEY, pastryResource.data);
                        adapter.submitList(pastryResource.data);
                        mBinding.progressBar.setVisibility(View.GONE);
                        mBinding.errorText.setVisibility(View.GONE);
                    } else {
                        mBinding.progressBar.setVisibility(View.GONE);
                        mBinding.errorText.setVisibility(View.VISIBLE);
                        mBinding.errorText.setText(getString(R.string.no_data_to_display));
                    }
                    break;
                case ERROR:
                    mBinding.progressBar.setVisibility(View.GONE);
                    mBinding.errorText.setVisibility(View.VISIBLE);
                    mBinding.errorText.setText(getString(R.string.error_has_occurred, pastryResource.message));
                    break;
                case LOADING:
                    mBinding.progressBar.setVisibility(View.VISIBLE);
                    mBinding.errorText.setVisibility(View.GONE);
                    break;
            }
        });
    }

    private void onPastryClick(Pastry pastry) {
        Intent intent = new Intent(this, PastryDetailActivity.class);
        intent.putExtra(Constants.PASTRY_ID, pastry.getId());
        startActivity(intent);
    }
}
