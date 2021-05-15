package com.d9tilov.android.tiertestapp.map.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.d9tilov.android.tiertestapp.base.ui.BaseViewModel
import com.d9tilov.android.tiertestapp.base.ui.BookingNavigator
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import kotlinx.coroutines.launch

class BookVehicleViewModel @ViewModelInject constructor(
    private val vehicleInteractor: VehicleInteractor,
    @Assisted val savedStateHandle: SavedStateHandle
) : BaseViewModel<BookingNavigator>() {

    private val vehicle = MutableLiveData<Vehicle>()

    init {
        val id = savedStateHandle.get<Long>(BookVehicleFragment.EXTRA_VEHICLE_ID)
        id?.run {
            viewModelScope.launch {
                vehicle.value = vehicleInteractor.getById(id)
            }
        }
    }

    fun getVehicle(): LiveData<Vehicle> = vehicle
}
