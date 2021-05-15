package com.d9tilov.android.tiertestapp.vehicle.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.d9tilov.android.tiertestapp.base.ui.wrapper.ResultData
import com.d9tilov.android.tiertestapp.base.ui.BaseViewModel
import com.d9tilov.android.tiertestapp.base.ui.MapNavigator
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VehicleViewModel @ViewModelInject constructor(vehicleInteractor: VehicleInteractor) :
    BaseViewModel<MapNavigator>() {

    private val vehicleList = MutableLiveData<ResultData<List<Vehicle>>>()

    init {
        viewModelScope.launch {
            vehicleInteractor.getAll()
                .catch {
                    vehicleList.postValue(ResultData.error(it.toString()))
                }.collect { vehicleList.value = ResultData.success(it) }
        }
    }

    fun goToSettings() {
        navigator?.openSettings()
    }

    fun openBookMenu(vehicleId: Long) {
        navigator?.openBookMenu(vehicleId)
    }

    fun vehicles(): LiveData<ResultData<List<Vehicle>>> = vehicleList
}
