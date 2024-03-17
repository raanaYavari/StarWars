package com.raana.starwars.data.film

import com.raana.starwars.data.film.model.FilmDetailNetwork
import retrofit2.http.GET
import retrofit2.http.Url

interface FilmApi {
    /**
     * Get Film content by given url from server
     *
     * @return Contents as FilmDetailNetwork Objects
     */
    @GET
    suspend fun getFilm(@Url url: String): FilmDetailNetwork
}