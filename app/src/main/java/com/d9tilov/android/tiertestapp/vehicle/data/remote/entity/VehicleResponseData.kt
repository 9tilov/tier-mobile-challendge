package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.squareup.moshi.Json

data class VehicleResponseData(

    @field:Json(name = "current")
    val current: List<RemoteVehicle> = arrayListOf(),
    @field:Json(name = "stats")
    val stats: RemoteStats
)
