package com.d9tilov.android.tiertestapp.vehicle.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.d9tilov.android.tiertestapp.base.ui.BaseViewModel
import com.d9tilov.android.tiertestapp.base.ui.MapNavigator
import com.d9tilov.android.tiertestapp.base.ui.wrapper.ResultData
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(vehicleInteractor: VehicleInteractor) :
    BaseViewModel<MapNavigator>() {

    val vehicleList: LiveData<ResultData<List<Vehicle>>> =
        vehicleInteractor.getAll()
            .map { ResultData.success(it) }
            .catch { emit(ResultData.error(it.toString())) }
            .asLiveData()

    fun goToSettings() {
        navigator?.openSettings()
    }

    fun openBookMenu(vehicleId: Long) {
        navigator?.openBookMenu(vehicleId)
    }
}
