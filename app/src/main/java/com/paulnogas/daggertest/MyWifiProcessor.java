package com.paulnogas.daggertest;

import android.content.SharedPreferences;
import android.net.wifi.WifiConfiguration;
import android.util.Log;

import javax.inject.Inject;

public class MyWifiProcessor implements WifiProcessor {

    private MyWiFiManagerInterface myWiFiManagerInterface;
    private SharedPreferences sharedPreferences;

    @Inject
    public MyWifiProcessor(MyWiFiManagerInterface myWiFiManagerInterface, SharedPreferences sharedPreferences) {
        this.myWiFiManagerInterface = myWiFiManagerInterface;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void doAddWifi() {
        WifiConfiguration config = makeWifiConfig();
        if (myWiFiManagerInterface.addWifi(config)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SSID", config.SSID);
            editor.putString("password", config.preSharedKey);
            editor.apply();
        } else {
            Log.e("DaggerTest", "doAddWifi Failed");
        }
    }

    @Override
    public void doRemoveWifi() {
        WifiConfiguration config = makeWifiConfig();
        if (myWiFiManagerInterface.removeWifi(config)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("SSID", "");
            editor.putString("password", "");
            editor.apply();
        } else {
            Log.e("DaggerTest", "doAddWifi Failed");
        }
    }

    private WifiConfiguration makeWifiConfig() {
        String ssid = sharedPreferences.getString("SSID", "");
        String password = sharedPreferences.getString("password", "");
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = ssid;
        wifiConfiguration.preSharedKey = password;
        wifiConfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        return wifiConfiguration;
    }

}
