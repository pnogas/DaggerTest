package com.paulnogas.daggertest;

import android.app.enterprise.EnterpriseDeviceManager;
import android.app.enterprise.WifiAdminProfile;
import android.content.Context;
import android.net.wifi.WifiConfiguration;

import javax.inject.Inject;


public class SamsungWifiManager implements MyWiFiManagerInterface {

    private EnterpriseDeviceManager enterpriseDeviceManager;

    @SuppressWarnings("WrongConstant")
    @Inject
    public SamsungWifiManager(Context context) {
        enterpriseDeviceManager = (EnterpriseDeviceManager) context.getSystemService(
                EnterpriseDeviceManager.ENTERPRISE_POLICY_SERVICE);
    }

    @Override
    public boolean addWifi(WifiConfiguration config) {
        WifiAdminProfile profile = createWifiProfile(config);
        return enterpriseDeviceManager.getWifiPolicy().setWifiProfile(profile);
    }

    private WifiAdminProfile createWifiProfile(WifiConfiguration config) {
        WifiAdminProfile adminProfile = new WifiAdminProfile();
        adminProfile.ssid = config.SSID;
        adminProfile.psk = config.preSharedKey;
        adminProfile.security = "PSK";
        return adminProfile;
    }

    @Override
    public boolean removeWifi(WifiConfiguration config) {
        return enterpriseDeviceManager.getWifiPolicy().removeNetworkConfiguration(config.SSID);
    }
}
