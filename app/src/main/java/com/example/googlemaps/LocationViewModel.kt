package com.example.googlemaps

import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class LocationViewModel:ViewModel() {
    private var fusedLocationClient:FusedLocationProviderClient?=null
}