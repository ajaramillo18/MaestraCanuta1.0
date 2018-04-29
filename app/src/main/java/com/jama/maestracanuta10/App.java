package com.jama.maestracanuta10;

import android.content.Context;

/**
 * Created by casa on 11/11/2017.
 */

public class App extends android.app.Application {
    public static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
