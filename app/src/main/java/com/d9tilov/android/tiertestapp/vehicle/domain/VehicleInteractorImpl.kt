package com.d9tilov.android.tiertestapp.vehicle.domain

import android.util.Log
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class VehicleInteractorImpl(
    private val vehicleRepo: VehicleRepo,
    private val settingsInteractor: SettingsInteractor
) : VehicleInteractor {

    override fun getAll(): Flow<List<Vehicle>> {
        return vehicleRepo.getAll().flatMapConcat { list ->
            settingsInteractor.getSettings()
                .map { settings ->
                    Log.d("moggot", "getAll: $settings")
                    val filterModelSet = settings.models.toSet()
                    list.filter { it.battery >= settings.chargeLevel }
                        .filter { filterModelSet.contains(it.model) }
                }
                .catch { emit(list) }
        }
    }

    override suspend fun getById(id: Long): Vehicle = vehicleRepo.getById(id)

    override fun getAllModels(): Flow<List<VehicleModel>> = vehicleRepo.getAllModels()

    override suspend fun insertAll(vehicles: List<Vehicle>) {
        vehicleRepo.insertAll(vehicles)
    }
}
