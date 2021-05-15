package com.d9tilov.android.tiertestapp.vehicle.data.remote.mapper

import com.d9tilov.android.tiertestapp.base.mapper.Mapper
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.data.remote.entity.VehicleResponse
import javax.inject.Inject

class VehicleRemoteMapper @Inject constructor() : Mapper<VehicleResponse, List<Vehicle>> {

    override fun toDataModel(dbModel: VehicleResponse): List<Vehicle> {
        return dbModel.data.current.map {
            Vehicle(
                uid = it.id,
                vehicleId = it.vehicleId,
                battery = it.battery,
                model = VehicleModel(it.model),
                latitude = it.latitude,
                longitude = it.longitude
            )
        }
    }

    override fun toDbModel(dataModel: List<Vehicle>): VehicleResponse {
        TODO("Not yet implemented")
    }
}
