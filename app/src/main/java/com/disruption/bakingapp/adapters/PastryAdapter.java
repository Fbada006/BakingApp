package com.disruption.bakingapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.bakingapp.databinding.ListItemPastryBinding;
import com.disruption.bakingapp.model.Pastry;

public class PastryAdapter extends ListAdapter<Pastry, PastryAdapter.PastryViewHolder> {
    private final PastryClickListener mPastryClickListener;

    public PastryAdapter(PastryClickListener PastryClickListener) {
        super(DIFF_CALLBACK);
        mPastryClickListener = PastryClickListener;
    }

    @NonNull
    @Override
    public PastryAdapter.PastryViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        ListItemPastryBinding binding =
                ListItemPastryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PastryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PastryViewHolder holder, final int position) {
        Pastry pastry = getItem(position);
        holder.bind(pastry, mPastryClickListener);
    }

    class PastryViewHolder extends RecyclerView.ViewHolder {

        private ListItemPastryBinding mPastryBinding;

        PastryViewHolder(ListItemPastryBinding binding) {
            super(binding.getRoot());
            mPastryBinding = binding;
        }

        private void bind(Pastry pastry, PastryClickListener pastryClickListener) {
            mPastryBinding.setPastry(pastry);
            mPastryBinding.setClickListener(pastryClickListener);
        }
    }

    private static final DiffUtil.ItemCallback<Pastry> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Pastry>() {
                @Override
                public boolean areItemsTheSame(@NonNull Pastry oldItem, @NonNull Pastry newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Pastry oldItem, @NonNull Pastry newItem) {
                    return oldItem == newItem;
                }
            };

    public interface PastryClickListener {
        void onPastryClickListener(Pastry Pastry);
    }
}
