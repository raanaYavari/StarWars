package com.raana.starwars.data.film

import com.raana.starwars.data.base.BaseRepositoryImpl
import com.raana.starwars.domain.film.FilmRepository
import com.raana.starwars.domain.film.result.FilmDetail
import javax.inject.Inject


/**
 * Film repository impl
 *
 * @property FilmApi
 * @constructor Create repository repository impl
 */
class FilmRepositoryImpl @Inject constructor(
    private val filmApi: FilmApi
) : FilmRepository, BaseRepositoryImpl() {
    override suspend fun getFilmDetail(urls: List<String>): Result<List<FilmDetail>> {
        val films = mutableListOf<FilmDetail>()
        return getResult {
            urls.forEach {
                films.add(filmApi.getFilm(it).toDomainModel())
            }
            films
        }
    }
}