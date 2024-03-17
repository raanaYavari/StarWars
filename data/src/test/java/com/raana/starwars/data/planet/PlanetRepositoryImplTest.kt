package com.raana.starwars.data.planet

import com.raana.starwars.data.helper.Constants
import com.raana.starwars.data.helper.createRetrofit
import com.raana.starwars.data.helper.setBodyFromFile
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
class PlanetRepositoryImplTest {

    private val mockWebServer = MockWebServer()
    private lateinit var planetApi: PlanetApi
    private lateinit var planetRepositoryImpl: PlanetRepositoryImpl

    @Before
    fun setup() {
        planetApi = createRetrofit(mockWebServer).create(PlanetApi::class.java)
        planetRepositoryImpl = PlanetRepositoryImpl(planetApi)
    }

    @After
    fun clean() {
        mockWebServer.shutdown()
    }

    @Test
    fun getPlanetByUrl_responseIsSuccessful_PlanetShouldBeReturned() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBodyFromFile("planet_detail_response.json")
        )
        var planet: PlanetDetail? = null
        runTest {
            planetRepositoryImpl.getPlanetDetail(Constants.URLS.PLANET_URL).onSuccess {
                planet = it
            }
            Assert.assertNotNull(planet)
        }
    }

}