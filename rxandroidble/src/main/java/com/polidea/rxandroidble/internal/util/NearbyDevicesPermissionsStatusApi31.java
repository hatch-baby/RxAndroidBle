package com.polidea.rxandroidble.internal.util;

import android.os.Build;

import com.polidea.rxandroidble.ClientComponent;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;

public class NearbyDevicesPermissionsStatusApi31 implements NearbyDevicesPermissionsStatus {

    private final CheckerNearbyDevicesPermission checkerNearbyDevicesPermission;
    private final int deviceSdk;

    @Inject
    NearbyDevicesPermissionsStatusApi31(
            CheckerNearbyDevicesPermission checkerNearbyDevicesPermission,
            @Named(ClientComponent.PlatformConstants.INT_DEVICE_SDK) int deviceSdk
    ) {
        this.deviceSdk = deviceSdk;
        this.checkerNearbyDevicesPermission = checkerNearbyDevicesPermission;
    }

    public boolean isNearbyDevicesPermissionOk() {
        if (deviceSdk < Build.VERSION_CODES.S) {
            // Not required because location provider and location permissions are used in this case
            return true;
        } else {
            return checkerNearbyDevicesPermission.isNearbyDevicesPermissionGranted();
        }
    }
}
