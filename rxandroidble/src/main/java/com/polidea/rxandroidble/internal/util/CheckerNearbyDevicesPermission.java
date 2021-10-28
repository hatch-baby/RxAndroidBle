package com.polidea.rxandroidble.internal.util;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;

import bleshadow.javax.inject.Inject;

public class CheckerNearbyDevicesPermission {

    private final Context context;

    @Inject
    public CheckerNearbyDevicesPermission(Context context) {
        this.context = context;
    }

    boolean isNearbyDevicesPermissionGranted() {
        if (Math.min(Build.VERSION.SDK_INT, provideTargetSdk()) >= 31) {
            return isPermissionGranted(Manifest.permission.BLUETOOTH_SCAN)
                    || isPermissionGranted(Manifest.permission.BLUETOOTH_CONNECT);
        } else {
            return false;
        }
    }

    /**
     * Copied from android.support.v4.content.ContextCompat for backwards compatibility
     *
     * @param permission the permission to check
     * @return true is granted
     */
    private boolean isPermissionGranted(String permission) {
        if (permission == null) {
            throw new IllegalArgumentException("permission is null");
        }

        return context.checkPermission(permission, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED;
    }

    private int provideTargetSdk() {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion;
        } catch (Throwable catchThemAll) {
            return Integer.MAX_VALUE;
        }
    }
}
