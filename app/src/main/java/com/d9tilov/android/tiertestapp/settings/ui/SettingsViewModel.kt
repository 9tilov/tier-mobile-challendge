package com.d9tilov.android.tiertestapp.settings.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.d9tilov.android.tiertestapp.base.ui.BaseViewModel
import com.d9tilov.android.tiertestapp.base.ui.SettingsNavigator
import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel @ViewModelInject constructor(
    private val vehicleInteractor: VehicleInteractor,
    private val settingsInteractor: SettingsInteractor
) : BaseViewModel<SettingsNavigator>() {

    private val settingsData = MutableLiveData<SettingsData>()
    private val modelsData = MutableLiveData<List<VehicleModel>>()

    init {
        viewModelScope.launch {
            val models = vehicleInteractor.getAllModels().first()
            modelsData.value = models
            settingsInteractor.getSettings()
                .catch {
                    settingsInteractor.createSettings(
                        SettingsData(
                            chargeLevel = 15,
                            models = models
                        )
                    )
                }
                .collect { settingsData.value = it }
        }
    }

    fun updateChargeLevel(level: Int) = viewModelScope.launch {
        val data = settingsData.value
        data?.let { settingsInteractor.updateSettings(it.copy(chargeLevel = level)) }
    }

    fun updateModel(model: VehicleModel, isChecked: Boolean) = viewModelScope.launch {
        val data = settingsData.value
        data?.let {
            val modelSet = it.models.toMutableSet()
            when (isChecked) {
                true -> modelSet.add(model)
                false -> modelSet.remove(model)
            }
            settingsInteractor.updateSettings(data.copy(models = modelSet.toList()))
        }
    }

    fun settings(): LiveData<SettingsData> = settingsData
    fun models(): LiveData<List<VehicleModel>> = modelsData
}
