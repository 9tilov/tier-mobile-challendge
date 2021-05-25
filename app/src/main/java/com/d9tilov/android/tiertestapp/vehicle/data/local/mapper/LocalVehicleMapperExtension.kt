package com.d9tilov.android.tiertestapp.vehicle.data.local.mapper

import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.data.local.entity.VehicleDbModel

fun VehicleDbModel.toDataModel() =
    Vehicle(id, uid, vehicleId, battery, VehicleModel(model), latitude, longitude)

fun Vehicle.toDbModel() =
    VehicleDbModel(id, uid, vehicleId, battery, model.name, latitude, longitude)
