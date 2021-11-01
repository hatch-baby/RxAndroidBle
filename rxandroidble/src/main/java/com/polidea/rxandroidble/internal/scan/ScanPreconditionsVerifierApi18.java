package com.polidea.rxandroidble.internal.scan;

import com.polidea.rxandroidble.exceptions.BleScanException;
import com.polidea.rxandroidble.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble.internal.util.NearbyDevicesPermissionsStatus;
import com.polidea.rxandroidble.internal.util.RxBleAdapterWrapper;

import bleshadow.javax.inject.Inject;

@SuppressWarnings("WeakerAccess")
public class ScanPreconditionsVerifierApi18 implements ScanPreconditionsVerifier {

    final RxBleAdapterWrapper rxBleAdapterWrapper;

    final LocationServicesStatus locationServicesStatus;
    final NearbyDevicesPermissionsStatus nearbyDevicesPermissionsStatus;

    @Inject
    public ScanPreconditionsVerifierApi18(
            RxBleAdapterWrapper rxBleAdapterWrapper,
            LocationServicesStatus locationServicesStatus,
            NearbyDevicesPermissionsStatus nearbyDevicesPermissionsStatus
    ) {
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
        this.locationServicesStatus = locationServicesStatus;
        this.nearbyDevicesPermissionsStatus = nearbyDevicesPermissionsStatus;
    }

    @Override
    public void verify() {
        if (!rxBleAdapterWrapper.hasBluetoothAdapter()) {
            throw new BleScanException(BleScanException.BLUETOOTH_NOT_AVAILABLE);
        } else if (!rxBleAdapterWrapper.isBluetoothEnabled()) {
            throw new BleScanException(BleScanException.BLUETOOTH_DISABLED);
        } else if (!nearbyDevicesPermissionsStatus.isNearbyDevicesPermissionOk()) {
            throw new BleScanException(BleScanException.NEARBY_DEVICES_PERMISSION_MISSING);
        } else if (!locationServicesStatus.isLocationPermissionOk()) {
            throw new BleScanException(BleScanException.LOCATION_PERMISSION_MISSING);
        } else if (!locationServicesStatus.isLocationProviderOk()) {
            throw new BleScanException(BleScanException.LOCATION_SERVICES_DISABLED);
        }
    }
}
