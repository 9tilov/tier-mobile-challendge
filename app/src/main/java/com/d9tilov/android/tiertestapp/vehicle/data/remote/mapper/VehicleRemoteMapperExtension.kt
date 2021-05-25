package com.d9tilov.android.tiertestapp.vehicle.data.remote.mapper

import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.data.remote.entity.VehicleResponse

fun VehicleResponse.toDataModel(): List<Vehicle> = data.current.map {
    Vehicle(
        uid = it.id,
        vehicleId = it.vehicleId,
        battery = it.battery,
        model = VehicleModel(it.model),
        latitude = it.latitude,
        longitude = it.longitude
    )
}
