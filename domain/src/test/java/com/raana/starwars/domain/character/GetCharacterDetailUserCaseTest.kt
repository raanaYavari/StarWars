package com.raana.starwars.domain.character

import com.raana.starwars.domain.character.result.CharacterDetail
import com.raana.starwars.domain.character.usecase.GetCharacterDetailUseCase
import com.raana.starwars.domain.character.usecase.GetCharacterListUseCase
import com.raana.starwars.domain.mock.MockDatabase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCharacterDetailUserCaseTest {
    private lateinit var mockCharacterRepository: CharacterRepository
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Before
    fun setUp() {
        mockCharacterRepository = mockk(relaxed = true)
        getCharacterDetailUseCase = GetCharacterDetailUseCase(mockCharacterRepository)
    }

    @Test
    fun getCharacterDetailUseCaseShouldReturnCharacters() {
        val character = MockDatabase.provideMockCharacterDetail()
        var response :CharacterDetail?
        coEvery {
            getCharacterDetailUseCase.invoke("")
        } returns Result.success(
            character
        ).also {
            response = it.getOrNull()
        }
        Assert.assertNotNull(response)
    }
}