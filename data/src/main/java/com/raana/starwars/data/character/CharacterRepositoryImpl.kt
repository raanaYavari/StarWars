package com.raana.starwars.data.character

import com.raana.starwars.data.base.BaseRepositoryImpl
import com.raana.starwars.domain.character.CharacterRepository
import com.raana.starwars.domain.character.result.CharacterDetail
import javax.inject.Inject


/**
 * Character repository impl
 *
 * @property characterApi
 * @constructor Create repository repository impl
 */
class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
) : CharacterRepository, BaseRepositoryImpl() {
    override suspend fun getCharacterList(search: String): Result<List<CharacterDetail>> {
        return getResult {
            characterApi.getCharacterList(search).results.map { it.toDomainModel() }
        }
    }

    override suspend fun getCharacter(url: String): Result<CharacterDetail> {
        return getResult {
            characterApi.getCharacter(url).toDomainModel()
        }
    }
}