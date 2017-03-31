package com.paulnogas.daggertest;

import android.net.wifi.WifiConfiguration;

public interface MyWiFiManagerInterface {

    boolean addWifi(WifiConfiguration config);

    boolean removeWifi(WifiConfiguration config);
}
