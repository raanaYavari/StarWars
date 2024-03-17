package com.raana.starwars.data.base.di

import com.raana.starwars.data.character.CharacterApi
import com.raana.starwars.data.film.FilmApi
import com.raana.starwars.data.planet.PlanetApi
import com.raana.starwars.data.species.SpeciesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Api module
 *
 * @constructor Create empty Api module
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    /**
     * provide Character api
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi =
        retrofit.create(CharacterApi::class.java)
    /**
     * provide Planet api
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun providePlanetApi(retrofit: Retrofit): PlanetApi = retrofit.create(PlanetApi::class.java)
    /**
     * provide Film api
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun provideFilmApi(retrofit: Retrofit): FilmApi = retrofit.create(FilmApi::class.java)
    /**
     * provide Species api
     *
     * @param retrofit
     * @return
     */
    @Provides
    @Singleton
    fun provideSpeciesApi(retrofit: Retrofit): SpeciesApi = retrofit.create(SpeciesApi::class.java)

}