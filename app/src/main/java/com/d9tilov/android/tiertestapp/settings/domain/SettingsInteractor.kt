package com.d9tilov.android.tiertestapp.settings.domain

import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import kotlinx.coroutines.flow.Flow

interface SettingsInteractor {

    fun getSettings(): Flow<SettingsData>
    suspend fun createSettings(data: SettingsData)
    suspend fun updateSettings(data: SettingsData)
}
