package com.paulnogas.daggertest;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;

import dagger.Module;
import dagger.Provides;

@Module
public class PostPermissionActivityModule {
    private static final String WIFI_SHARED_PREFS = "wifi";
    private final Context context;

    public PostPermissionActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(){
        return context.getSharedPreferences(WIFI_SHARED_PREFS, Context.MODE_PRIVATE);
    }

    @Provides
    public WifiManager provideWifiManager() {
        return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    @Provides
    public WifiProcessor provideWifiProcessor(MyWiFiManagerInterface myWiFiManagerInterface, SharedPreferences sharedPreferences){
        return new MyWifiProcessor(myWiFiManagerInterface, sharedPreferences);
    }
}
