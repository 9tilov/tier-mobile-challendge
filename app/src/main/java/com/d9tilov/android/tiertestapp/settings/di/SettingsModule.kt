package com.d9tilov.android.tiertestapp.settings.di

import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase
import com.d9tilov.android.tiertestapp.settings.data.SettingsDataRepo
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsLocalSource
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsSource
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractorImpl
import com.d9tilov.android.tiertestapp.settings.domain.SettingsRepo
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class SettingsModule {

    @Provides
    @ActivityRetainedScoped
    fun provideSettingsSource(
        appDatabase: AppDatabase
    ): SettingsSource = SettingsLocalSource(appDatabase.settingsDao())

    @Provides
    @ActivityRetainedScoped
    fun provideSettingsRepo(
        settingsSource: SettingsSource
    ): SettingsRepo = SettingsDataRepo(settingsSource)

    @Provides
    @ActivityRetainedScoped
    fun provideSettingsInteractor(
        settingsRepo: SettingsRepo,
        vehicleRepo: VehicleRepo
    ): SettingsInteractor = SettingsInteractorImpl(vehicleRepo, settingsRepo)
}
