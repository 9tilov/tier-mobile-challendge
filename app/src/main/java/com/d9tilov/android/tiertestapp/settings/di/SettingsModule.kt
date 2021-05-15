package com.d9tilov.android.tiertestapp.settings.di

import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase
import com.d9tilov.android.tiertestapp.settings.data.SettingsDataRepo
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsLocalSource
import com.d9tilov.android.tiertestapp.settings.data.local.SettingsSource
import com.d9tilov.android.tiertestapp.settings.data.local.mapper.SettingsDataMapper
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractorImpl
import com.d9tilov.android.tiertestapp.settings.domain.SettingsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class SettingsModule {

    @Provides
    fun provideSettingsSource(
        appDatabase: AppDatabase,
        settingsDataMapper: SettingsDataMapper
    ): SettingsSource = SettingsLocalSource(appDatabase, settingsDataMapper)

    @Provides
    fun provideSettingsRepo(
        settingsSource: SettingsSource,
        mapper: SettingsDataMapper
    ): SettingsRepo = SettingsDataRepo(settingsSource)

    @Provides
    fun provideSettingsInteractor(
        settingsRepo: SettingsRepo,
    ): SettingsInteractor = SettingsInteractorImpl(settingsRepo)
}
