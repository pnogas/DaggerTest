package com.paulnogas.daggertest;


import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {PostPermissionActivityModule.class, MyWifFiManagerInterfaceModule.class})
@Singleton
public interface PostPermissionActivityComponent {
    void inject(PostPermissionActivity postPermissionActivity);
    void inject(MyWiFiManagerInterface myWiFiManagerInterface);
}
