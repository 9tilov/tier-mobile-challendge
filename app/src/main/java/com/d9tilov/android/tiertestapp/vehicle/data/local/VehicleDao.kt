package com.d9tilov.android.tiertestapp.vehicle.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.d9tilov.android.tiertestapp.vehicle.data.local.entity.VehicleDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicles")
    fun getAll(): Flow<List<VehicleDbModel>>

    @Query("SELECT * FROM vehicles WHERE id=:id")
    suspend fun getById(id: Long): VehicleDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleDbModel)

    @Update
    suspend fun update(vehicle: VehicleDbModel)
}
