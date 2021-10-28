package com.polidea.rxandroidble.internal.util;

import com.polidea.rxandroidble.ClientComponent;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;

public class NearbyDevicesPermissionsStatusApi31 implements NearbyDevicesPermissionsStatus {

    private final CheckerNearbyDevicesPermission checkerNearbyDevicesPermission;
    private final int targetSdk;

    @Inject
    NearbyDevicesPermissionsStatusApi31(
            CheckerNearbyDevicesPermission checkerNearbyDevicesPermission,
            @Named(ClientComponent.PlatformConstants.INT_TARGET_SDK) int targetSdk
    ) {
        this.targetSdk = targetSdk;
        this.checkerNearbyDevicesPermission = checkerNearbyDevicesPermission;
    }

    public boolean isNearbyDevicesPermissionOk() {
        return checkerNearbyDevicesPermission.isNearbyDevicesPermissionGranted();
    }
}
