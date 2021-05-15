package com.d9tilov.android.tiertestapp.map.di

import androidx.lifecycle.ProcessLifecycleOwner
import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.vehicle.data.InMemoryModel
import com.d9tilov.android.tiertestapp.vehicle.data.VehicleDataRepo
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleLocalSource
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleSource
import com.d9tilov.android.tiertestapp.vehicle.data.local.mapper.VehicleDataMapper
import com.d9tilov.android.tiertestapp.vehicle.data.remote.mapper.VehicleRemoteMapper
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractor
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleInteractorImpl
import com.d9tilov.android.tiertestapp.vehicle.domain.VehicleRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
class MapModule {

    @Provides
    @ActivityRetainedScoped
    fun provideVehicleSource(
        appDatabase: AppDatabase,
        dataMapper: VehicleDataMapper
    ): VehicleSource =
        VehicleLocalSource(appDatabase, dataMapper)

    @Provides
    @ActivityRetainedScoped
    fun provideInMemoryModel(source: VehicleSource): InMemoryModel = InMemoryModel(
        ProcessLifecycleOwner.get().lifecycle, source
    )

    @Provides
    @ActivityRetainedScoped
    fun provideVehicleRepo(
        vehicleSource: VehicleSource,
        remoteMapper: VehicleRemoteMapper,
        inMemoryModel: InMemoryModel,
        retrofit: Retrofit
    ): VehicleRepo =
        VehicleDataRepo(vehicleSource, remoteMapper, inMemoryModel, retrofit)

    @Provides
    @ActivityRetainedScoped
    fun provideVehicleInteractor(
        vehicleRepo: VehicleRepo,
        settingsInteractor: SettingsInteractor
    ): VehicleInteractor = VehicleInteractorImpl(vehicleRepo, settingsInteractor)
}
