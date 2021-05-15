package com.d9tilov.android.tiertestapp.settings.data

import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsSource
import com.d9tilov.android.tiertestapp.settings.domain.SettingsRepo
import kotlinx.coroutines.flow.Flow

class SettingsDataRepo(
    private val settingsSource: SettingsSource
) : SettingsRepo {

    override fun getSettings(): Flow<SettingsData> =
        settingsSource.getSettings()

    override suspend fun createSettings(data: SettingsData) {
        settingsSource.createSettings(data)
    }

    override suspend fun updateSettings(data: SettingsData) {
        settingsSource.updateSettings(data)
    }
}
