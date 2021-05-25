package com.d9tilov.android.tiertestapp.vehicle.data

import androidx.lifecycle.LifecycleObserver
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleSource
import com.d9tilov.android.tiertestapp.vehicle.data.remote.VehicleApi
import com.d9tilov.android.tiertestapp.vehicle.data.remote.mapper.toDataModel
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class VehicleDataRepo(
    private val vehicleSource: VehicleSource,
    private val inMemoryModel: InMemoryModel,
    private val vehicleApi: VehicleApi
) : VehicleRepo, LifecycleObserver {

    override fun getAll(): Flow<List<Vehicle>> {
        return vehicleSource.getAll().flatMapConcat { localList ->
            flow { emit(vehicleApi.getVehicles().toDataModel()) }
                .catch {
                    emit(localList)
                }
                .map {
                    inMemoryModel.addAll(it)
                    when (localList.isEmpty()) {
                        true -> vehicleSource.insertAll(it)
                        false -> vehicleSource.updateAll(it)
                    }
                    localList
                }
        }
    }

    override suspend fun getById(id: Long): Vehicle = vehicleSource.getById(id)
    override suspend fun insertAll(vehicles: List<Vehicle>) {
        vehicleSource.insertAll(vehicles)
    }

    override fun getAllModels(): Flow<List<VehicleModel>> {
        val models = mutableSetOf<VehicleModel>()
        return if (inMemoryModel.cachedList.isEmpty()) {
            vehicleSource.getAll().map {
                it.forEach { item -> models.add(item.model) }
                models.toList()
            }
        } else {
            inMemoryModel.cachedList.forEach { models.add(it.model) }
            flowOf(models.toList())
        }
    }
}