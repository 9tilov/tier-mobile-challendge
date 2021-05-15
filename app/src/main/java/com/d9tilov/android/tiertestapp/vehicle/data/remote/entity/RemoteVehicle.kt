package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteVehicle(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("vehicleId")
    @Expose
    val vehicleId: String,
    @SerializedName("hardwareId")
    @Expose
    val hardwareId: String,
    @SerializedName("zoneId")
    @Expose
    val zoneId: String,
    @SerializedName("resolution")
    @Expose
    val resolution: String,
    @SerializedName("resolvedBy")
    @Expose
    val resolvedBy: String,
    @SerializedName("resolvedAt")
    @Expose
    val resolvedAt: String,
    @SerializedName("battery")
    @Expose
    val battery: Int,
    @SerializedName("state")
    @Expose
    val state: String,
    @SerializedName("model")
    @Expose
    val model: String,
    @SerializedName("fleetbirdId")
    @Expose
    val fleetbirdId: Int,
    @SerializedName("latitude")
    @Expose
    val latitude: Double,
    @SerializedName("longitude")
    @Expose
    val longitude: Double
)
