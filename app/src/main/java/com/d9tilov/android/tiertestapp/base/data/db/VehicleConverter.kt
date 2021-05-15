package com.d9tilov.android.tiertestapp.base.data.db

import androidx.room.TypeConverter
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object VehicleConverter {

    @TypeConverter
    @JvmStatic
    fun fromModelListToString(list: List<VehicleModel>): String {
        return Gson().toJson(list).toString()
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToModelList(value: String): List<VehicleModel> {
        val turnsType = object : TypeToken<List<VehicleModel>>() {}.type
        return Gson().fromJson<List<VehicleModel>>(value, turnsType)
    }
}
