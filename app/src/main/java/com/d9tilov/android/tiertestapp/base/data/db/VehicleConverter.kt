package com.d9tilov.android.tiertestapp.base.data.db

import androidx.room.TypeConverter
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.Collections

object VehicleConverter {

    private val moshi = Moshi.Builder().build()
    private val membersType = Types.newParameterizedType(List::class.java, VehicleModel::class.java)
    private val membersAdapter = moshi.adapter<List<VehicleModel>>(membersType)

    @TypeConverter
    fun fromJson(str: String): List<VehicleModel> {
        return membersAdapter.fromJson(str) ?: Collections.emptyList()
    }

    @TypeConverter
    fun toJson(list: List<VehicleModel>): String {
        return membersAdapter.toJson(list)
    }
}
