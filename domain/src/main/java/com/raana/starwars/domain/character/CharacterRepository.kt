package com.raana.starwars.domain.character

import com.raana.starwars.domain.character.result.CharacterDetail


interface CharacterRepository {
    /**
     * Get CharacterDetail List content from remote Data Source
     *
     * @return List of CharacterDetail objects
     */
    suspend fun getCharacterList(search: String): Result<List<CharacterDetail>>

    /**
     * Get CharacterDetail content from remote Data Source
     *
     * @return CharacterDetail objects
     */
    suspend fun getCharacter(url: String): Result<CharacterDetail>
}