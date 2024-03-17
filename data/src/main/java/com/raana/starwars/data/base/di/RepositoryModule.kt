package com.raana.starwars.data.base.di

import com.raana.starwars.data.character.CharacterRepositoryImpl
import com.raana.starwars.data.film.FilmRepositoryImpl
import com.raana.starwars.data.planet.PlanetRepositoryImpl
import com.raana.starwars.data.species.SpeciesRepositoryImpl
import com.raana.starwars.domain.character.CharacterRepository
import com.raana.starwars.domain.film.FilmRepository
import com.raana.starwars.domain.planet.PlanetRepository
import com.raana.starwars.domain.species.SpeciesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


/**
 * Repository module
 *
 * @constructor Create empty Repository module
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    /**
     * Bind Character repository
     *
     * @param repository
     * @return
     */
    @Binds
    abstract fun bindCharacterRepository(repository: CharacterRepositoryImpl): CharacterRepository
    /**
     * Bind Planet repository
     *
     * @param repository
     * @return
     */
    @Binds
    abstract fun bindPlanetRepository(repository: PlanetRepositoryImpl): PlanetRepository
    /**
     * Bind Film repository
     *
     * @param repository
     * @return
     */
    @Binds
    abstract fun bindFilmRepository(repository: FilmRepositoryImpl): FilmRepository
    /**
     * Bind Species repository
     *
     * @param repository
     * @return
     */
    @Binds
    abstract fun bindSpeciesRepository(repository: SpeciesRepositoryImpl): SpeciesRepository

}