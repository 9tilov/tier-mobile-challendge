package com.d9tilov.android.tiertestapp.vehicle.domain

import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import kotlinx.coroutines.flow.Flow

interface VehicleRepo {

    fun getAll(): Flow<List<Vehicle>>
    suspend fun getById(id: Long): Vehicle
    suspend fun insertAll(vehicles: List<Vehicle>)
    fun getAllModels(): Flow<List<VehicleModel>>
}
