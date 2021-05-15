package com.d9tilov.android.tiertestapp.settings.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings")
    fun get(): Flow<SettingsDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SettingsDbModel)

    @Update
    suspend fun update(data: SettingsDbModel)
}
