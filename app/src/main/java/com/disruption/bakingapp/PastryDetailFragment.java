package com.disruption.bakingapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.disruption.bakingapp.model.Ingredient;
import com.disruption.bakingapp.utils.Constants;
import com.disruption.bakingapp.viewmodels.PastryViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * A fragment representing a single Pastry detail screen.
 * This fragment is either contained in a {@link PastryListActivity}
 * in two-pane mode (on tablets) or a {@link PastryDetailActivity}
 * on handsets.
 */
public class PastryDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_PASTRY_NAME = "ingredient_name";

    /**
     * The dummy content this fragment is presenting.
     */
    private Ingredient mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PastryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final PastryViewModel viewModel = new ViewModelProvider(this).get(PastryViewModel.class);

        if (getArguments().containsKey(ARG_PASTRY_NAME)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getParcelable(ARG_PASTRY_NAME);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                //appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pastry_fragment_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.pastry_detail)).setText("Details");
        }

        return rootView;
    }
}
