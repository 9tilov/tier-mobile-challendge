package com.d9tilov.android.tiertestapp.vehicle.data.local

import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.local.mapper.VehicleDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VehicleLocalSource(
    appDatabase: AppDatabase,
    private val dataMapper: VehicleDataMapper
) : VehicleSource {

    private val vehicleDao = appDatabase.vehicleDao()

    override fun getAll(): Flow<List<Vehicle>> = vehicleDao.getAll()
        .map { list -> list.map { dataMapper.toDataModel(it) } }

    override suspend fun getById(id: Long): Vehicle = dataMapper.toDataModel(vehicleDao.getById(id))

    override suspend fun insertAll(vehicles: List<Vehicle>) {
        vehicles.map { vehicleDao.insert(dataMapper.toDbModel(it)) }
    }

    override suspend fun updateAll(vehicles: List<Vehicle>) {
        vehicles.map { vehicleDao.update(dataMapper.toDbModel(it)) }
    }
}
