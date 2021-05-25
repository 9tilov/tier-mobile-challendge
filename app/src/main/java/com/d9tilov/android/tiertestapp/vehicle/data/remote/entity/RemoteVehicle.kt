package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.squareup.moshi.Json

data class RemoteVehicle(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "vehicleId")
    val vehicleId: String,
    @field:Json(name = "hardwareId")
    val hardwareId: String,
    @field:Json(name = "zoneId")
    val zoneId: String,
    @field:Json(name = "resolution")
    val resolution: String,
    @field:Json(name = "resolvedBy")
    val resolvedBy: String,
    @field:Json(name = "resolvedAt")
    val resolvedAt: String,
    @field:Json(name = "battery")
    val battery: Int,
    @field:Json(name = "state")
    val state: String,
    @field:Json(name = "model")
    val model: String,
    @field:Json(name = "fleetbirdId")
    val fleetbirdId: Int,
    @field:Json(name = "latitude")
    val latitude: Double,
    @field:Json(name = "longitude")
    val longitude: Double
)
