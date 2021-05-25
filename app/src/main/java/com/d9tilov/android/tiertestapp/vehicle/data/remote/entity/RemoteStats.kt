package com.d9tilov.android.tiertestapp.vehicle.data.remote.entity

import com.squareup.moshi.Json

data class RemoteStats(
    @field:Json(name = "open")
    val open: Int?,
    @field:Json(name="assigned")
    val assigned: Int?,
    @field:Json(name="resolved")
    val resolved: Int?
)
