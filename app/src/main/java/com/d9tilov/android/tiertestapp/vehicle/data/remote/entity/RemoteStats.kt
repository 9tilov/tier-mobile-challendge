package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemoteStats(
    @SerializedName("open")
    @Expose
    val open: Int?,
    @SerializedName("assigned")
    @Expose
    val assigned: Int?,
    @SerializedName("resolved")
    @Expose
    val resolved: Int?
)
