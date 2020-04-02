package com.disruption.bakingapp;

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
import com.disruption.bakingapp.databinding.ActivityPastryListBinding;
import com.disruption.bakingapp.dummy.DummyContent;

/**
 * An activity representing a list of Pastries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PastryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PastryListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

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

        if (findViewById(R.id.pastry_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.pastry_list);
        RecyclerView recyclerView1 = findViewById(R.id.pastry_list1);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
        if (recyclerView1 != null) {
            setupRecyclerView1(recyclerView1);
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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        final PastryIngredientsAdapter pastryIngredientsAdapter = new PastryIngredientsAdapter();
        pastryIngredientsAdapter.submitList(DummyContent.ITEMS);
        recyclerView.setAdapter(pastryIngredientsAdapter);
    }

    private void setupRecyclerView1(@NonNull RecyclerView recyclerView) {
        final PastryStepsAdapter ingredientsAdapter = new PastryStepsAdapter(this, mTwoPane);
        ingredientsAdapter.submitList(DummyContent.ITEMS);
        recyclerView.setAdapter(ingredientsAdapter);
    }
}
