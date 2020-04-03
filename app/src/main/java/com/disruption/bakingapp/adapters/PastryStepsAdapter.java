package com.disruption.bakingapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.bakingapp.StepVideoActivity;
import com.disruption.bakingapp.StepVideoFragment;
import com.disruption.bakingapp.PastryDetailActivity;
import com.disruption.bakingapp.R;
import com.disruption.bakingapp.model.Step;
import com.disruption.bakingapp.utils.Constants;

import org.jetbrains.annotations.NotNull;

public class PastryStepsAdapter
        extends ListAdapter<Step, PastryStepsAdapter.ViewHolder> {

    private final PastryDetailActivity mParentActivity;
    private final boolean mTwoPane;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Step step = (Step) view.getTag();
            if (!TextUtils.isEmpty(step.getVideoURL())) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(Constants.ARG_STEP_EXTRA, step);
                    StepVideoFragment fragment = new StepVideoFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.pastry_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, StepVideoActivity.class);
                    intent.putExtra(Constants.ARG_STEP_EXTRA, step);
                    context.startActivity(intent);
                }
            }
        }
    };

    public PastryStepsAdapter(PastryDetailActivity parent,
                              boolean twoPane) {
        super(DIFF_CALLBACK);
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_list_content_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Step step = getItem(position);
        holder.mIdView.setText(step.getDescription());
        if (hasVideo(step)) {
            holder.mPlay.setVisibility(View.VISIBLE);
        } else {
            holder.mPlay.setVisibility(View.GONE);
        }

        holder.itemView.setTag(step);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final ImageView mPlay;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.step_description);
            mPlay = view.findViewById(R.id.iv_step_video);
        }
    }

    private static final DiffUtil.ItemCallback<Step> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Step>() {
                @Override
                public boolean areItemsTheSame(@NonNull Step oldItem, @NonNull Step newItem) {
                    return oldItem.getDescription().equals(newItem.getDescription());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Step oldItem, @NonNull Step newItem) {
                    return oldItem == newItem;
                }
            };

    private boolean hasVideo(Step step) {
        return !TextUtils.isEmpty(step.getVideoURL());
    }
}
