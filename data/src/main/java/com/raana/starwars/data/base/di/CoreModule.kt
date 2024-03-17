package com.raana.starwars.data.base.di

import android.content.Context
import com.raana.starwars.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Core module
 *
 * @constructor Create empty Core module
 */
@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    /**
     * Provide retrofit
     *
     * @param okHttpClient
     * @return
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.CONNECTION_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    /**
     * Provide ok http
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.BUILD_TYPE != "release") {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                }
            }

        return httpClient.build()
    }



}

