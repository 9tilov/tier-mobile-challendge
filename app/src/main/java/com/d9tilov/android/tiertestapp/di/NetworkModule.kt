package com.d9tilov.android.tiertestapp.di

import android.util.Log
import com.d9tilov.android.tiertestapp.App
import com.d9tilov.android.tiertestapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("Content-Type", "application/json")
                builder.header("secret-key", BuildConfig.API_KEY)
                return@Interceptor chain.proceed(builder.build())
            })
            .build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(App.TAG, "HTTP REQUEST: $message")
            }
        }).apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    companion object {
        private const val READ_TIMEOUT = 15L
        private const val WRITE_TIMEOUT = 15L
        const val BASE_URL = "https://api.jsonbin.io/"
    }
}
