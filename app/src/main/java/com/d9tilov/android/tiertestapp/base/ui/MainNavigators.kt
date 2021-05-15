package com.d9tilov.android.tiertestapp.base.ui

interface MapNavigator : BaseNavigator {
    fun openSettings()
    fun openBookMenu(vehicleId: Long)
}

interface SettingsNavigator : BaseNavigator {
    fun save()
}

interface BookingNavigator : BaseNavigator {
    fun apply()
}
