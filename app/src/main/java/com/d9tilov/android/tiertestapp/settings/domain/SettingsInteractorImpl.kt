package com.d9tilov.android.tiertestapp.settings.domain

import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow

class SettingsInteractorImpl(
    private val vehicleRepo: VehicleRepo,
    private val settingsRepo: SettingsRepo,
) : SettingsInteractor {

    override fun getSettings(): Flow<SettingsData> {
        return vehicleRepo.getAllModels().flatMapConcat { models ->
            run {
                settingsRepo.getSettings().distinctUntilChanged()
                    .catch {
                        val createdSettings = SettingsData(
                            chargeLevel = 15,
                            models = models
                        )
                        createSettings(createdSettings)
                        flow { emit(createdSettings) }
                    }
            }
        }
    }

    override suspend fun createSettings(data: SettingsData) {
        settingsRepo.createSettings(data)
    }

    override suspend fun updateSettings(data: SettingsData) {
        settingsRepo.updateSettings(data)
    }
}
