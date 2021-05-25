package com.d9tilov.android.tiertestapp.settings.data.local.mapper

import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsDbModel

fun SettingsDbModel.toDataModel() = SettingsData(id, chargeLevel, models)

fun SettingsData.toDbModel() = SettingsDbModel(id, chargeLevel, models)
