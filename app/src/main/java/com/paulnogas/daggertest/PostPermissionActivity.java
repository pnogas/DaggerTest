package com.paulnogas.daggertest;

import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

public class PostPermissionActivity extends AppCompatActivity {

    private static final String SAMSUNG = "samsung";

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    WifiProcessor wifiProcessor;

    @Inject
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_permission);
        doInjection();
    }

    private void doInjection() {
        MyWiFiManagerInterface chosenWiFiImplementation;
        if (SAMSUNG.equals(Build.MANUFACTURER)) {
            chosenWiFiImplementation = new SamsungWifiManager(getApplicationContext());
        } else {
            chosenWiFiImplementation = new AOSPWiFiManager(wifiManager);
        }
        PostPermissionActivityComponent postPermissionActivityComponent = DaggerPostPermissionActivityComponent.builder()
                .postPermissionActivityModule(new PostPermissionActivityModule(this))
                .myWifFiManagerInterfaceModule(new MyWifFiManagerInterfaceModule(chosenWiFiImplementation))
                .build();
        postPermissionActivityComponent.inject(this);
        postPermissionActivityComponent.inject(chosenWiFiImplementation);
    }


    public void onSetClicked(View view) {
        EditText ssidEditText = (EditText) findViewById(R.id.ssid_edit_text);
        EditText passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SSID", ssidEditText.getText().toString());
        editor.putString("password", passwordEditText.getText().toString());
        editor.apply();
        wifiProcessor.doAddWifi();
    }

    public void onRemoveClicked(View view) {
        wifiProcessor.doRemoveWifi();
    }
}
