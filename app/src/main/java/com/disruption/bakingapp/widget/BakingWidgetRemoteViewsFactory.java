package com.disruption.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.disruption.bakingapp.R;
import com.disruption.bakingapp.data.TinyDb;
import com.disruption.bakingapp.model.Pastry;
import com.disruption.bakingapp.utils.Constants;

import java.util.List;

public class BakingWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = "BakingWidgetRemoteViews";

    private Context mContext;
    private List<Pastry> mPastries;

    public BakingWidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
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
        remoteViews.setImageViewResource(R.id.widget_pastry_image, R.drawable.brownies);

        Bundle extras = new Bundle();
        extras.putInt("NOTE_POSITION", position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        remoteViews.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

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
