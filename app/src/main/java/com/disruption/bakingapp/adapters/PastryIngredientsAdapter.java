package com.disruption.bakingapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.bakingapp.R;
import com.disruption.bakingapp.dummy.DummyContent;

import org.jetbrains.annotations.NotNull;

public class PastryIngredientsAdapter
        extends ListAdapter<DummyContent.DummyItem, PastryIngredientsAdapter.ViewHolder> {

    public PastryIngredientsAdapter() {
        super(DIFF_CALLBACK);
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
