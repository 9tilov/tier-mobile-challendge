package com.d9tilov.android.tiertestapp.vehicle.data.remote

import com.d9tilov.android.tiertestapp.vehicle.data.remote.entity.VehicleResponse
import retrofit2.http.GET

interface VehicleApi {

    @GET("b/5fa8ff8dbd01877eecdb898f")
    suspend fun getVehicles(): VehicleResponse
}
