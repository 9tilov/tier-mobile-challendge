package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.squareup.moshi.Json

data class VehicleResponse(
    @field:Json(name = "data")
    val data: VehicleResponseData
)
