package com.raana.starwars.data.film.model

import com.google.gson.annotations.SerializedName
import com.raana.starwars.domain.film.result.FilmDetail
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailNetwork(
    val title: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
) {
    fun toDomainModel() = FilmDetail(title, openingCrawl)
}