package com.disruption.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.bakingapp.adapters.PastryIngredientsAdapter;
import com.disruption.bakingapp.adapters.PastryStepsAdapter;
import com.disruption.bakingapp.data.TinyDb;
import com.disruption.bakingapp.databinding.ActivityPastryDetailBinding;
import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.utils.Constants;

import java.util.List;


public class PastryDetailActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    /**
     * The pastry passed from the MainActivity as an extra
     */
    private Pastry mPastry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPastryDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_pastry_detail);

        setSupportActionBar(binding.toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TinyDb tinyDb = new TinyDb(this);

        List<Pastry> listOfPastries = tinyDb.getListOfPastries(Constants.PASTRY_LIST_KEY);

        if (findViewById(R.id.pastry_detail_container) != null) {
            mTwoPane = true;
        }

        //Retrieve the intent containing the pastry
        Intent intent = getIntent();
        RecyclerView ingredientsListRv = binding.pastryListLayout.ingredientsList;
        RecyclerView stepsListRv = binding.pastryListLayout.stepsList;

        if (intent != null && intent.hasExtra(Constants.PASTRY_ID)) {
            int id = intent.getIntExtra(Constants.PASTRY_ID, 0);
            mPastry =
                    TinyDb.getPastryFromId(id, listOfPastries);

            if (mPastry != null) {
                tinyDb.putInt(Constants.OUTSTATE_PASTRY_ID, mPastry.getId());
            }

            setupIngredientsRecyclerView(ingredientsListRv);
            setupStepsRecyclerView(stepsListRv);

            setTitle(mPastry.getName());
        }

        int savedId = tinyDb.getInt(Constants.OUTSTATE_PASTRY_ID);
        if (savedId != 0) {
            //This is a saved id of the current Pastry
            mPastry =
                    TinyDb.getPastryFromId(savedId, listOfPastries);

            if (mPastry != null) {
                tinyDb.putInt(Constants.OUTSTATE_PASTRY_ID, mPastry.getId());
            }
            setupIngredientsRecyclerView(ingredientsListRv);
            setupStepsRecyclerView(stepsListRv);
            setTitle(mPastry.getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupIngredientsRecyclerView(@NonNull RecyclerView recyclerView) {
        final PastryIngredientsAdapter pastryIngredientsAdapter = new PastryIngredientsAdapter();
        if (mPastry != null) {
            pastryIngredientsAdapter.submitList(mPastry.getIngredients());
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(pastryIngredientsAdapter);
    }

    private void setupStepsRecyclerView(@NonNull RecyclerView recyclerView) {
        final PastryStepsAdapter ingredientsAdapter = new PastryStepsAdapter(this, mTwoPane);
        if (mPastry != null) {
            ingredientsAdapter.submitList(mPastry.getSteps());
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(ingredientsAdapter);
    }
}
