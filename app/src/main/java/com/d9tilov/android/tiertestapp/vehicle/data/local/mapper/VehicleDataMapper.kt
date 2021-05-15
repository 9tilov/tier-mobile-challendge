package com.d9tilov.android.tiertestapp.vehicle.data.local.mapper

import com.d9tilov.android.tiertestapp.base.mapper.Mapper
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.data.local.entity.VehicleDbModel
import javax.inject.Inject

class VehicleDataMapper @Inject constructor() : Mapper<VehicleDbModel, Vehicle> {

    override fun toDataModel(dbModel: VehicleDbModel): Vehicle =
        with(dbModel) {
            Vehicle(id, uid, vehicleId, battery, VehicleModel(model), latitude, longitude)
        }

    override fun toDbModel(dataModel: Vehicle): VehicleDbModel =
        with(dataModel) {
            VehicleDbModel(id, uid, vehicleId, battery, model.name, latitude, longitude)
        }
}
