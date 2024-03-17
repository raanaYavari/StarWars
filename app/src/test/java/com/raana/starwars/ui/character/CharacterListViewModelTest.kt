package com.raana.starwars.ui.character

import com.raana.starwars.base.ModelWrapper
import com.raana.starwars.domain.character.usecase.GetCharacterListUseCase
import com.raana.starwars.mapper.toCharacterView
import com.raana.starwars.rule.MainDispatcherRule
import com.raana.starwars.ui.mock.MockDatabase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelTest {

    private lateinit var mockGetCharacterListUseCase: GetCharacterListUseCase
    private lateinit var characterListViewModel: CharacterListViewModel

    @get: Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        mockGetCharacterListUseCase = mockk(relaxed = true)
        characterListViewModel =
            CharacterListViewModel(
                mockGetCharacterListUseCase,
            )
    }

    @Test
    fun getCharacterList_isSuccess_updatesCharacterListStateWithModelWrapperSuccess() = runTest {
        val characterData = listOf(
           MockDatabase.provideMockCharacterDetail()
        )
        coEvery {
            mockGetCharacterListUseCase.invoke("")
        } returns Result.success(
            characterData
        )
        characterListViewModel.getCharacterList("")
        val response = characterListViewModel.currentState.characters

        Assert.assertTrue(response is ModelWrapper.Success)
        Assert.assertNotNull((response as ModelWrapper.Success).result)
    }

    @Test
    fun getCharacterList_isSuccess_updatesCharacterListDataCorrect() = runTest {
        val characterData = listOf(
            MockDatabase.provideMockCharacterDetail()
        )
        coEvery {
            mockGetCharacterListUseCase.invoke("")
        } returns Result.success(
            characterData
        )
        characterListViewModel.getCharacterList("")
        val response = characterListViewModel.currentState.characters

        Assert.assertEquals(response.data, characterData.map { it.toCharacterView() })
    }


    @Test
    fun getCharacterList_isFailed_updatesCharacterListStateWithModelWrapperFailed() = runTest {
        coEvery {
            mockGetCharacterListUseCase.invoke("")
        } returns Result.failure(
            Exception()
        )
        characterListViewModel.getCharacterList("")
        val response = characterListViewModel.currentState.characters

        Assert.assertTrue(response is ModelWrapper.Failure)
    }

    @Test
    fun getCharacterList_isFailed_updatesCharacterListFailureCorrect() = runTest {
        val throwable = Throwable("Something bad happened")
        coEvery {
            mockGetCharacterListUseCase.invoke("")
        } returns Result.failure(
            throwable
        )
        characterListViewModel.getCharacterList("")
        val response = characterListViewModel.currentState.characters

        Assert.assertEquals(throwable, (response as ModelWrapper.Failure).throwable)
    }
}