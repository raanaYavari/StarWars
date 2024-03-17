package com.raana.starwars.domain.character.usecase

import com.raana.starwars.domain.base.BaseUseCase
import com.raana.starwars.domain.character.CharacterRepository
import com.raana.starwars.domain.character.result.CharacterDetail
import javax.inject.Inject

/**
 * Get CharacterDetail use case
 *
 * @property repository CharacterRepository to connect to Data Layer and get response from Data Sources
 */
class GetCharacterDetailUseCase @Inject constructor(private val repository: CharacterRepository) :
    BaseUseCase<String, Result<CharacterDetail>>() {
    override suspend fun invoke(param: String): Result<CharacterDetail> =
        repository.getCharacter(param)

}