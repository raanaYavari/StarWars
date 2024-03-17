package com.raana.starwars.data.film

import com.raana.starwars.data.helper.Constants
import com.raana.starwars.data.helper.createRetrofit
import com.raana.starwars.data.helper.setBodyFromFile
import com.raana.starwars.data.planet.PlanetApi
import com.raana.starwars.data.planet.PlanetRepositoryImpl
import com.raana.starwars.domain.film.result.FilmDetail
import com.raana.starwars.domain.planet.result.PlanetDetail
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
class FilmRepositoryImplTest {

    private val mockWebServer = MockWebServer()
    private lateinit var filmApi: FilmApi
    private lateinit var filmRepositoryImpl: FilmRepositoryImpl

    @Before
    fun setup() {
        filmApi = createRetrofit(mockWebServer).create(FilmApi::class.java)
        filmRepositoryImpl = FilmRepositoryImpl(filmApi)
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
                .setBodyFromFile("film_response.json")
        )
        var films = listOf<FilmDetail>()
        runTest {
            filmRepositoryImpl.getFilmDetail(arrayListOf(Constants.URLS.FILM_URL)).onSuccess {
                films = it
            }
            Assert.assertEquals(films.size, 1)
        }
    }

}