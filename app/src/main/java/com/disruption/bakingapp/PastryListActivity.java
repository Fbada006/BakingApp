package com.disruption.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.bakingapp.adapters.PastryIngredientsAdapter;
import com.disruption.bakingapp.adapters.PastryStepsAdapter;
import com.disruption.bakingapp.data.TinyDb;
import com.disruption.bakingapp.databinding.ActivityPastryListBinding;
import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.utils.Constants;

import java.util.List;

/**
 * An activity representing a list of Pastries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PastryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PastryListActivity extends AppCompatActivity {

    private static final String TAG = "PastryListActivity";

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
        ActivityPastryListBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_pastry_list);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TinyDb tinyDb = new TinyDb(this);

        List<Pastry> listOfPastries = tinyDb.getListOfPastries(Constants.PASTRY_LIST_KEY);

        if (findViewById(R.id.pastry_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
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

            if (ingredientsListRv != null) {
                setupIngredientsRecyclerView(ingredientsListRv);
            }

            if (stepsListRv != null) {
                setupStepsRecyclerView(stepsListRv);
            }
        }

        int savedId = tinyDb.getInt(Constants.OUTSTATE_PASTRY_ID);
        if (savedId != 0) {
            mPastry =
                    TinyDb.getPastryFromId(savedId, listOfPastries);

            if (mPastry != null) {
                tinyDb.putInt(Constants.OUTSTATE_PASTRY_ID, mPastry.getId());
            }

            if (ingredientsListRv != null) {
                setupIngredientsRecyclerView(ingredientsListRv);
            }

            if (stepsListRv != null) {
                setupStepsRecyclerView(stepsListRv);
            }
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
        recyclerView.setAdapter(pastryIngredientsAdapter);
    }

    private void setupStepsRecyclerView(@NonNull RecyclerView recyclerView) {
        final PastryStepsAdapter ingredientsAdapter = new PastryStepsAdapter(this, mTwoPane);
        if (mPastry != null) {
            ingredientsAdapter.submitList(mPastry.getSteps());
        }
        recyclerView.setAdapter(ingredientsAdapter);
    }
}
