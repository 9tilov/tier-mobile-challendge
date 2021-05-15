package com.d9tilov.android.tiertestapp.settings.domain

import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class SettingsInteractorImpl(
    private val settingsRepo: SettingsRepo,
) : SettingsInteractor {

    override fun getSettings(): Flow<SettingsData> =
        settingsRepo.getSettings().distinctUntilChanged()

    override suspend fun createSettings(data: SettingsData) {
        settingsRepo.createSettings(data)
    }

    override suspend fun updateSettings(data: SettingsData) {
        settingsRepo.updateSettings(data)
    }
}
