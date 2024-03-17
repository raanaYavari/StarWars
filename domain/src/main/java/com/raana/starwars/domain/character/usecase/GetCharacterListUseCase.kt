package com.raana.starwars.domain.character.usecase

import com.raana.starwars.domain.base.BaseUseCase
import com.raana.starwars.domain.character.CharacterRepository
import com.raana.starwars.domain.character.result.CharacterDetail
import javax.inject.Inject

/**
 * Get CharacterList use case
 *
 * @property repository CharacterRepository to connect to Data Layer and get response from Data Sources
 */
class GetCharacterListUseCase @Inject constructor(private val repository: CharacterRepository) :
    BaseUseCase<String, Result<List<CharacterDetail>>>() {
    override suspend fun invoke(param: String): Result<List<CharacterDetail>> =
        repository.getCharacterList(param)
}