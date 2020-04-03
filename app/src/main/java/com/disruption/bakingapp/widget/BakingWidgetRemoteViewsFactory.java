package com.disruption.bakingapp.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.disruption.bakingapp.R;
import com.disruption.bakingapp.data.TinyDb;
import com.disruption.bakingapp.model.Ingredient;
import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.utils.Constants;

import java.util.List;

public class BakingWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Pastry> mPastries;

    public BakingWidgetRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mPastries = new TinyDb(mContext.getApplicationContext()).getListOfPastries(Constants.PASTRY_LIST_KEY);
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return mPastries.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_widget_pastry);
        Pastry pastry = mPastries.get(position);
        remoteViews.setTextViewText(R.id.pastry_title, pastry.getName());

        if (position <= getCount()) {
            StringBuilder sb = new StringBuilder();
            for (Ingredient ingredient : pastry.getIngredients()) {
                sb.append(mContext.getString(R.string.ingredient_details, ingredient.getIngredient(),
                        String.valueOf(ingredient.getQuantity()), ingredient.getMeasure())).append("\n\n");
            }
            String ingredients = sb.toString().trim();
            remoteViews.setTextViewText(R.id.pastry_ingredients, ingredients);
        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
