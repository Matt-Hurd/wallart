package com.wallart.pp.android.starter

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import butterknife.ButterKnife
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(DebugTree())
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        requestPermissions()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun requestPermissions() {
        val neededPermissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE)
        var hasAllPermissions = true
        neededPermissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        neededPermissions,
                        MY_PERMISSIONS_REQUEST_CALLBACK)
                hasAllPermissions = false
            }
        }
        if (hasAllPermissions) {
//            onLocationPermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CALLBACK -> {
                if (grantResults.isNotEmpty()) {
//                    onLocationPermissionGranted()
                } else {
                    requestPermissions()
                }
                return
            }
        }
    }

    companion object {
        val MY_PERMISSIONS_REQUEST_CALLBACK = 1234
    }
}
