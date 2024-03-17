package com.raana.starwars.data.character

import com.raana.starwars.data.character.model.CharacterDetailNetwork
import com.raana.starwars.data.character.model.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface CharacterApi {

    /**
     * Get Character List content from server
     *
     * @return List of Contents as CharacterDetailNetwork Objects
     */
    @GET("people")
    suspend fun getCharacterList(@Query("search") search: String): CharacterSearchResponse
    /**
     * Get Character Detail content by given url from server
     *
     * @return Contents as CharacterDetailNetwork Objects
     */
    @GET
    suspend fun getCharacter(@Url url: String): CharacterDetailNetwork
}