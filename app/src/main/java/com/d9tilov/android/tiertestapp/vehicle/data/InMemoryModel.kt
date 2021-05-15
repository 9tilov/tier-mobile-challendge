package com.d9tilov.android.tiertestapp.vehicle.data

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.d9tilov.android.tiertestapp.vehicle.data.entity.Vehicle
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InMemoryModel(
    lifecycle: Lifecycle,
    private val source: VehicleSource
) : LifecycleObserver {

    val cachedList = arrayListOf<Vehicle>()

    init {
        lifecycle.addObserver(this)
    }

    fun addAll(vehicles: List<Vehicle>) {
        cachedList.addAll(vehicles)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun flush() {
        GlobalScope.launch {
            source.updateAll(cachedList)
        }
    }
}