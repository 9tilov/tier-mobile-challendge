package com.d9tilov.android.tiertestapp.settings.data.local

import com.d9tilov.android.tiertestapp.base.data.Source
import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import kotlinx.coroutines.flow.Flow

interface SettingsSource : Source {

    fun getSettings(): Flow<SettingsData>
    suspend fun createSettings(data: SettingsData)
    suspend fun updateSettings(data: SettingsData)
}
