package com.paulnogas.daggertest;


import dagger.Module;
import dagger.Provides;

@Module
class MyWifFiManagerInterfaceModule {
    private MyWiFiManagerInterface myWiFiManagerInterface;

    public MyWifFiManagerInterfaceModule(MyWiFiManagerInterface chosenWiFiImplementation) {
        myWiFiManagerInterface = chosenWiFiImplementation;
    }

    @Provides
    public MyWiFiManagerInterface provideMyWiFiManagerInterface(){
        return myWiFiManagerInterface;
    }
}
