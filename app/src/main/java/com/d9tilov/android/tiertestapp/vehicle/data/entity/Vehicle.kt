package com.d9tilov.android.tiertestapp.vehicle.data.entity

import com.d9tilov.android.tiertestapp.constants.DataConstants.Companion.DEFAULT_DATA_ID

data class Vehicle(
    val id: Long = DEFAULT_DATA_ID,
    val uid: String,
    val vehicleId: String,
    val battery: Int,
    val model: VehicleModel,
    val latitude: Double,
    val longitude: Double
)
