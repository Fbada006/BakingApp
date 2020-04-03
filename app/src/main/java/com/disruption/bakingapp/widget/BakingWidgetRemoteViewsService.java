package com.disruption.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class BakingWidgetRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetRemoteViewsFactory(getApplicationContext());
    }
}
