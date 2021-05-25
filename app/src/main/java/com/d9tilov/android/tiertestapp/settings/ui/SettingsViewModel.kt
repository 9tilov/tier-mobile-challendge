package com.d9tilov.android.tiertestapp.settings.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.d9tilov.android.tiertestapp.base.ui.BaseViewModel
import com.d9tilov.android.tiertestapp.base.ui.SettingsNavigator
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val vehicleInteractor: VehicleInteractor,
    private val settingsInteractor: SettingsInteractor
) : BaseViewModel<SettingsNavigator>() {

    val settingsData = settingsInteractor.getSettings().asLiveData()
    val modelsData = vehicleInteractor.getAllModels().asLiveData()

    fun updateChargeLevel(level: Int) = viewModelScope.launch(Dispatchers.IO) {
        val data = settingsData.value
        data?.let { settingsInteractor.updateSettings(it.copy(chargeLevel = level)) }
    }

    fun updateModel(model: VehicleModel, isChecked: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            val data = settingsInteractor.getSettings().first()
            data.let {
                val modelSet = it.models.toMutableSet()
                when (isChecked) {
                    true -> modelSet.add(model)
                    false -> modelSet.remove(model)
                }
                settingsInteractor.updateSettings(data.copy(models = modelSet.toList()))
            }
        }
}
