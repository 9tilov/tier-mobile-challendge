package com.d9tilov.android.tiertestapp.settings.data.local

import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase
import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.settings.data.local.mapper.SettingsDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsLocalSource(
    appDatabase: AppDatabase,
    private val dataMapper: SettingsDataMapper
) : SettingsSource {

    private val settingsDao = appDatabase.settingsDao()

    override fun getSettings(): Flow<SettingsData> =
        settingsDao.get().map {
            it.let { dataMapper.toDataModel(it) }
        }

    override suspend fun createSettings(data: SettingsData) {
        settingsDao.insert(dataMapper.toDbModel(data))
    }

    override suspend fun updateSettings(data: SettingsData) {
        settingsDao.update(dataMapper.toDbModel(data))
    }
}
