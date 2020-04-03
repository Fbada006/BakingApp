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
import com.disruption.bakingapp.model.Ingredient;

import org.jetbrains.annotations.NotNull;

public class PastryIngredientsAdapter
        extends ListAdapter<Ingredient, PastryIngredientsAdapter.ViewHolder> {

    public PastryIngredientsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_list_content_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Ingredient item = getItem(position);
        holder.mIdView.setText(holder.itemView.getContext().getString(R.string.ingredient_details, item.getIngredient(),
                String.valueOf(item.getQuantity()), item.getMeasure()));

        holder.itemView.setTag(item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;

        ViewHolder(View view) {
            super(view);
            mIdView = view.findViewById(R.id.step_description);
        }
    }

    private static final DiffUtil.ItemCallback<Ingredient> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Ingredient>() {
                @Override
                public boolean areItemsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
                    return oldItem.getIngredient().equals(newItem.getIngredient());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Ingredient oldItem, @NonNull Ingredient newItem) {
                    return oldItem == newItem;
                }
            };
}
