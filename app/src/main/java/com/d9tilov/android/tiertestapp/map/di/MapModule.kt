package com.d9tilov.android.tiertestapp.map.di

import androidx.lifecycle.ProcessLifecycleOwner
import com.d9tilov.android.tiertestapp.base.data.db.AppDatabase
import com.d9tilov.android.tiertestapp.settings.domain.SettingsInteractor
import com.d9tilov.android.tiertestapp.vehicle.data.InMemoryModel
import com.d9tilov.android.tiertestapp.vehicle.data.VehicleDataRepo
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleLocalSource
import com.d9tilov.android.tiertestapp.vehicle.data.local.VehicleSource
import com.d9tilov.android.tiertestapp.vehicle.data.remote.VehicleApi
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
        appDatabase: AppDatabase
    ): VehicleSource =
        VehicleLocalSource(appDatabase.vehicleDao())

    @Provides
    @ActivityRetainedScoped
    fun provideInMemoryModel(source: VehicleSource): InMemoryModel = InMemoryModel(
        ProcessLifecycleOwner.get().lifecycle, source
    )

    @Provides
    @ActivityRetainedScoped
    fun provideVehicleRepo(
        vehicleSource: VehicleSource,
        inMemoryModel: InMemoryModel,
        retrofit: Retrofit
    ): VehicleRepo =
        VehicleDataRepo(vehicleSource, inMemoryModel,  retrofit.create(VehicleApi::class.java))

    @Provides
    @ActivityRetainedScoped
    fun provideVehicleInteractor(
        vehicleRepo: VehicleRepo,
        settingsInteractor: SettingsInteractor
    ): VehicleInteractor = VehicleInteractorImpl(vehicleRepo, settingsInteractor)
}
