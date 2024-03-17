package com.raana.starwars.data.species

import com.raana.starwars.data.film.FilmApi
import com.raana.starwars.data.film.FilmRepositoryImpl
import com.raana.starwars.data.helper.Constants
import com.raana.starwars.data.helper.createRetrofit
import com.raana.starwars.data.helper.setBodyFromFile
import com.raana.starwars.domain.film.result.FilmDetail
import com.raana.starwars.domain.species.result.SpeciesDetail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class SpeciesRepositoryImplTest {
    private val mockWebServer = MockWebServer()
    private lateinit var speciesApi: SpeciesApi
    private lateinit var speciesRepositoryImpl: SpeciesRepositoryImpl

    @Before
    fun setup() {
        speciesApi = createRetrofit(mockWebServer).create(SpeciesApi::class.java)
        speciesRepositoryImpl = SpeciesRepositoryImpl(speciesApi)
    }

    @After
    fun clean() {
        mockWebServer.shutdown()
    }

    @Test
    fun getFilmByUrls_responseIsSuccessful_FilmListShouldBeReturned() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBodyFromFile("specie_response.json")
        )
        var species = listOf<SpeciesDetail>()
        runTest {
            speciesRepositoryImpl.getSpeciesDetail(arrayListOf(Constants.URLS.SPECIE_URL)).onSuccess {
                species = it
            }
            Assert.assertEquals(species.size, 1)
        }
    }

}