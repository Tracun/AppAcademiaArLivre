package com.wb.tracun.appacademiaarlivre;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Tracun on 19/03/2017.
 */

public class AcademiaApp extends Application{

@Override
    public void onCreate(){
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
}


}
