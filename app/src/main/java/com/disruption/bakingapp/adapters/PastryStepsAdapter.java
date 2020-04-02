package com.disruption.bakingapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.bakingapp.PastryDetailActivity;
import com.disruption.bakingapp.PastryDetailFragment;
import com.disruption.bakingapp.PastryListActivity;
import com.disruption.bakingapp.R;
import com.disruption.bakingapp.dummy.DummyContent;

import org.jetbrains.annotations.NotNull;

public class PastryStepsAdapter
        extends ListAdapter<DummyContent.DummyItem, PastryStepsAdapter.ViewHolder> {

    private final PastryListActivity mParentActivity;
    private final boolean mTwoPane;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(PastryDetailFragment.ARG_ITEM_ID, item.id);
                PastryDetailFragment fragment = new PastryDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.pastry_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, PastryDetailActivity.class);
                intent.putExtra(PastryDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }
        }
    };

    public PastryStepsAdapter(PastryListActivity parent,
                              boolean twoPane) {
        super(DIFF_CALLBACK);
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pastry_list_content_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DummyContent.DummyItem item = getItem(position);
        holder.mIdView.setText(item.id);
        holder.mContentView.setText(item.content);

        holder.itemView.setTag(item);
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.id_text);
            mContentView = view.findViewById(R.id.content);
        }
    }

    private static final DiffUtil.ItemCallback<DummyContent.DummyItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DummyContent.DummyItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull DummyContent.DummyItem oldItem, @NonNull DummyContent.DummyItem newItem) {
                    return oldItem.id.equals(newItem.id);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull DummyContent.DummyItem oldItem, @NonNull DummyContent.DummyItem newItem) {
                    return oldItem == newItem;
                }
            };
}
