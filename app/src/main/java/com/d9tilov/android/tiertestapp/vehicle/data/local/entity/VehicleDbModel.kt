package com.d9tilov.android.tiertestapp.vehicle.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicles")
data class VehicleDbModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "uid") val uid: String,
    @ColumnInfo(name = "vehicleId") val vehicleId: String,
    @ColumnInfo(name = "battery") val battery: Int,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double

)
