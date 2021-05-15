package com.d9tilov.android.tiertestapp.settings.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel

@Entity(tableName = "settings")
data class SettingsDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "chargeLevel")
    val chargeLevel: Int,
    @ColumnInfo(name = "models")
    val models: List<VehicleModel>
)
