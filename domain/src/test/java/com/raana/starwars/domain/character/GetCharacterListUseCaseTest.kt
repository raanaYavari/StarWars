package com.raana.starwars.domain.character

import com.raana.starwars.domain.character.result.CharacterDetail
import com.raana.starwars.domain.character.usecase.GetCharacterListUseCase
import com.raana.starwars.domain.mock.MockDatabase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class GetCharacterListUseCaseTest {
    private lateinit var mockCharacterRepository: CharacterRepository
    private lateinit var getCharacterListUseCase: GetCharacterListUseCase

    @Before
    fun setUp() {
        mockCharacterRepository = mockk(relaxed = true)
        getCharacterListUseCase = GetCharacterListUseCase(mockCharacterRepository)
    }

    @Test
    fun getCharacterListUseCaseShouldReturnCharacters() {
        val characterData = listOf(
            MockDatabase.provideMockCharacterDetail()
        )
        var response: List<CharacterDetail>?
        coEvery {
            getCharacterListUseCase.invoke("")
        } returns Result.success(
            characterData
        ).also {
            response = it.getOrNull()
        }
        assertEquals(1, characterData.size)
    }
}