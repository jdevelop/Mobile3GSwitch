package com.example.Mobile3GSwitch;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * User: Eugene Dzhurinsky
 * Date: 3/16/13
 */
public class Switch3GWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_WIDGET_NOTIF = "com.example.Mobile3GSwitch.Switch3GWidgetProvider.UPDATE_ICON";

    public static RemoteViews rview;

    private static int idx = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d("OOOPS", "Update!");
        updateWidgetState(context, "");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("OOOPS", "Here we gooo!");
        String str = intent.getAction();
        if (str.equals(ACTION_WIDGET_NOTIF)) {
            updateWidgetState(context, str);
        } else {
            super.onReceive(context, intent);
        }
    }

    static void updateWidgetState(Context paramContext, String paramString) {
        RemoteViews localRemoteViews = buildUpdate(paramContext, paramString); //CALL HERE
        ComponentName localComponentName = new ComponentName(paramContext, Switch3GWidgetProvider.class);
        AppWidgetManager.getInstance(paramContext).updateAppWidget(localComponentName, localRemoteViews);
    }

    private static RemoteViews buildUpdate(Context paramContext, String paramString) {
        rview = new RemoteViews(paramContext.getPackageName(), R.layout.main);
        Intent active = new Intent(paramContext, Switch3GWidgetProvider.class);
        active.setAction(ACTION_WIDGET_NOTIF);
        PendingIntent actionPendingIntent = PendingIntent.getBroadcast(paramContext, 0, active, 0);
        rview.setOnClickPendingIntent(R.id.imageButton, actionPendingIntent);
        if (paramString.equals(ACTION_WIDGET_NOTIF)) {
            if (idx == 0) {
                rview.setImageViewResource(R.id.imageButton, R.drawable.off);
            } else {
                rview.setImageViewResource(R.id.imageButton, R.drawable.on);
            }
            idx = idx ^ 1;
        }
        return rview;
    }

}