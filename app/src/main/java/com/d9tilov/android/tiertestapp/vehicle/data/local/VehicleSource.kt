package com.d9tilov.android.tiertestapp.vehicle.data.local

import com.d9tilov.android.tiertestapp.base.data.Source
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleSource : Source {

    fun getAll(): Flow<List<Vehicle>>
    suspend fun getById(id: Long): Vehicle
    suspend fun insertAll(vehicles: List<Vehicle>)
    suspend fun updateAll(vehicles: List<Vehicle>)
}
