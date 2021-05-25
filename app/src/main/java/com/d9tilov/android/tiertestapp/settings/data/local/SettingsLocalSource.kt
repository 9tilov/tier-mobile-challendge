package com.d9tilov.android.tiertestapp.settings.data.local

import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.settings.data.local.mapper.toDataModel
import com.d9tilov.android.tiertestapp.settings.data.local.mapper.toDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsLocalSource(private val settingsDao: SettingsDao) : SettingsSource {

    override fun getSettings(): Flow<SettingsData> = settingsDao.get().map { it.toDataModel() }

    override suspend fun createSettings(data: SettingsData) {
        settingsDao.insert(data.toDbModel())
    }

    override suspend fun updateSettings(data: SettingsData) {
        settingsDao.update(data.toDbModel())
    }
}
