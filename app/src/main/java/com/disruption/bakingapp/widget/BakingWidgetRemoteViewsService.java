package com.disruption.bakingapp.widget;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class BakingWidgetRemoteViewsService extends RemoteViewsService {
    private static final String TAG = "BakingWidgetRemoteViews";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.e(TAG, "onGetViewFactory: =====================");
        return new BakingWidgetRemoteViewsFactory(getApplicationContext(), intent);
    }
}
