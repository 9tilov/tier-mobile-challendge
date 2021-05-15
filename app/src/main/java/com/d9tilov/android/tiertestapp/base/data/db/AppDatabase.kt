package com.d9tilov.android.tiertestapp.base.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase.Companion.VERSION_NUMBER
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsDao
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsDbModel
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleDao
import com.d9tilov.android.tiertestapp.vehicle.data.local.entity.VehicleDbModel

@Database(
    entities = [
        VehicleDbModel::class,
        SettingsDbModel::class
    ],
    version = VERSION_NUMBER,
    exportSchema = false
)

@TypeConverters(
    VehicleConverter::class
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        const val VERSION_NUMBER = 1
    }
}
