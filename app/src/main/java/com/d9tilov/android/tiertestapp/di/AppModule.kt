package com.d9tilov.android.tiertestapp.di

import android.app.Application
import android.content.Context
import com.d9tilov.android.tiertestapp.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideApplication(application: Application): App {
        return application as App
    }
}