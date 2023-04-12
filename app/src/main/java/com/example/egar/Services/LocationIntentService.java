package com.example.egar.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.egar.Manifest;

public class LocationIntentService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public LocationIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != PackageManager.PERMISSION_GRANTED) {

        }


//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//        if (location != null) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//
//            // يمكنك الآن استخدام latitude و longitude في تحديد الموقع
//        }
    }
}
