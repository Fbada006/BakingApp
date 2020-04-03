package com.disruption.bakingapp.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.disruption.bakingapp.R;
import com.disruption.bakingapp.model.Pastry;

public class BindingUtils {

    /**
     * This method checks if the pastry has an image. If it does not, simply load a drawable with the
     * same name in lowercase and lacking spaces. Otherwise, load the one from the JSON response
     */
    @BindingAdapter("imagePastry")
    public static void setPastryImage(ImageView imageView, Pastry pastry) {
        Context context = imageView.getContext();
        if (TextUtils.isEmpty(pastry.getImage())) {
            Glide.with(context)
                    .load(getPastryDrawable(context, pastry.getName().toLowerCase().replaceAll("\\s", "")))
                    .centerCrop()
                    .placeholder(R.drawable.pastry_loading_animation)
                    .error(R.drawable.ic_error)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(pastry.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.pastry_loading_animation)
                    .error(R.drawable.ic_error)
                    .into(imageView);
        }
    }

    public static int getPastryDrawable(Context context, String drawableName) {
        return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
    }
}
