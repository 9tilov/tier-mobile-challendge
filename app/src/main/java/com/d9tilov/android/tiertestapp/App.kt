package com.d9tilov.android.tiertestapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        const val TAG = "[TIER]"
    }
}