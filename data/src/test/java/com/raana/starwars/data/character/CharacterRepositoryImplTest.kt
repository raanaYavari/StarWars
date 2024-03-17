package com.raana.starwars.data.character

import com.raana.starwars.data.helper.Constants
import com.raana.starwars.data.helper.createRetrofit
import com.raana.starwars.data.helper.setBodyFromFile
import com.raana.starwars.domain.character.result.CharacterDetail
import org.junit.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {

    private val mockWebServer = MockWebServer()
    private lateinit var characterApi: CharacterApi
    private lateinit var characterRepositoryImpl: CharacterRepositoryImpl

    @Before
    fun setup() {
        characterApi = createRetrofit(mockWebServer).create(CharacterApi::class.java)
        characterRepositoryImpl = CharacterRepositoryImpl(characterApi)
    }

    @After
    fun clean() {
        mockWebServer.shutdown()
    }

    @Test
    fun getCharacterListBySearchQuery_responseIsSuccessful_CharacterListShouldBeReturned() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBodyFromFile("get_character_list_response.json")
        )
        val characterList = mutableListOf<CharacterDetail>()
        runTest {
            characterRepositoryImpl.getCharacterList(Constants.CHARACTER_NAME).onSuccess {
                characterList.add(it.first())
            }
            Assert.assertEquals(characterList.size, 1)
        }
    }

    @Test
    fun getCharacterDetailByUrl_responseIsSuccessful_CharacterDetailShouldBeReturned() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBodyFromFile("character_detail_response.json")
        )
        var character: CharacterDetail? = null
        runTest {
            characterRepositoryImpl.getCharacter(Constants.URLS.CHARACTER_URL).onSuccess {
                character = it
            }
            Assert.assertNotNull(character)
        }
    }
}