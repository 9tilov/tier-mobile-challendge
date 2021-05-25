package com.d9tilov.android.tiertestapp.map.ui

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.d9tilov.android.tiertestapp.base.ui.BaseViewModel
import com.d9tilov.android.tiertestapp.base.ui.BookingNavigator
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class BookVehicleViewModel @AssistedInject constructor(
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

    @AssistedFactory
    interface BookVehicleViewModelFactory {
        fun create(handle: SavedStateHandle): BookVehicleViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: BookVehicleViewModelFactory,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return assistedFactory.create(handle) as T
                }
            }
    }
}
