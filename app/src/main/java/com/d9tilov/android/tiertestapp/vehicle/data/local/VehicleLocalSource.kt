package com.d9tilov.android.tiertestapp.vehicle.data.local

import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.local.mapper.toDataModel
import com.d9tilov.android.tiertestapp.vehicle.data.local.mapper.toDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehicleLocalSource(private val vehicleDao: VehicleDao) : VehicleSource {

    override fun getAll(): Flow<List<Vehicle>> = vehicleDao.getAll()
        .map { list -> list.map { item -> item.toDataModel() } }

    override suspend fun getById(id: Long): Vehicle = vehicleDao.getById(id).toDataModel()

    override suspend fun insertAll(vehicles: List<Vehicle>) {
        vehicles.map { vehicleDao.insert(it.toDbModel()) }
    }

    override suspend fun updateAll(vehicles: List<Vehicle>) {
        vehicles.map { vehicleDao.update(it.toDbModel()) }
    }
}
