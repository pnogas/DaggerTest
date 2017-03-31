package com.paulnogas.daggertest;


import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

import javax.inject.Inject;

public class AOSPWiFiManager implements MyWiFiManagerInterface {
    private WifiManager wifiManager;

    @Inject
    public AOSPWiFiManager(WifiManager wifiManager) {
        this.wifiManager = wifiManager;
    }

    @Override
    public boolean addWifi(WifiConfiguration config) {
        int netId =  wifiManager.addNetwork(config);
        if (netId >= 0){
            wifiManager.enableNetwork(netId, false);
            wifiManager.saveConfiguration();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeWifi(WifiConfiguration config) {
        wifiManager.removeNetwork(config.networkId);
        wifiManager.saveConfiguration();
        return true;
    }
}
