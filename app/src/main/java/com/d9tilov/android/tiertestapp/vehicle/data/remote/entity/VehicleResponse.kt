package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VehicleResponse(

    @SerializedName("data")
    @Expose
    val data: VehicleResponseData
)
