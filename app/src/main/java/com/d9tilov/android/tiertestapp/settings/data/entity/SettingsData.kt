package com.d9tilov.android.tiertestapp.settings.data.entity

import com.d9tilov.android.tiertestapp.constants.DataConstants.Companion.DEFAULT_DATA_ID
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel

data class SettingsData(
    val id: Long = DEFAULT_DATA_ID,
    val chargeLevel: Int,
    val models: List<VehicleModel>
)
