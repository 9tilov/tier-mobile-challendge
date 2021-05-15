package com.d9tilov.android.tiertestapp.settings.data.local.mapper

import com.d9tilov.android.tiertestapp.base.mapper.Mapper
import com.d9tilov.android.tiertestapp.settings.data.entity.SettingsData
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsDbModel
import javax.inject.Inject

class SettingsDataMapper @Inject constructor() : Mapper<SettingsDbModel, SettingsData> {

    override fun toDataModel(dbModel: SettingsDbModel): SettingsData =
        with(dbModel) {
            SettingsData(id, chargeLevel, models)
        }

    override fun toDbModel(dataModel: SettingsData): SettingsDbModel =
        with(dataModel) {
            SettingsDbModel(id, chargeLevel, models)
        }
}
