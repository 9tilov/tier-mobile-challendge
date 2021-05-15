package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VehicleResponseData(

    @SerializedName("current")
    @Expose
    val current: List<RemoteVehicle> = arrayListOf(),
    @SerializedName("stats")
    @Expose
    val stats: RemoteStats
)
