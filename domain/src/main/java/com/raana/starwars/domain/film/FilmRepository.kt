package com.raana.starwars.domain.film

import com.raana.starwars.domain.film.result.FilmDetail

interface FilmRepository {
    /**
     * Get FilmDetail content from remote Data Source
     *
     * @return List of FilmDetail objects
     */
    suspend fun getFilmDetail(urls: List<String>): Result<List<FilmDetail>>
}