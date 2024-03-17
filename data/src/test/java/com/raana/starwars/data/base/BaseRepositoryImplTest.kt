package com.raana.starwars.data.base

import com.raana.starwars.data.helper.FakeApi
import com.raana.starwars.data.helper.createRetrofit
import com.raana.starwars.data.helper.setBodyFromFile
import org.junit.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class BaseRepositoryImplTest {

    private val mockWebServer = MockWebServer()
    private lateinit var baseRepositoryImpl: BaseRepositoryImpl

    @Before
    fun setup() {
        baseRepositoryImpl = BaseRepositoryImpl()
    }

    @After
    fun clean() {
        mockWebServer.shutdown()
    }

    @Test
    fun getResult_responseIsSuccessful_callsDoOnSuccessAndReturnsSuccessResult() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBodyFromFile("base_response.json")
        )
        val retrofit = createRetrofit(mockWebServer)
        var isSuccess = false
        runTest {
            val result = baseRepositoryImpl.getResult(doOnSuccess = {
                isSuccess = true
            }) {
                retrofit.create(FakeApi::class.java).fakeCall()
            }
            Assert.assertTrue(isSuccess)
            Assert.assertTrue(result.isSuccess)
        }
    }

    @Test
    fun getResult_responseIsSuccessfulButJsonIsNotValid_returnsFailedResult() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{}")
        )
        val retrofit = createRetrofit(mockWebServer)
        runTest {
            val result = baseRepositoryImpl.getResult {
                retrofit.create(FakeApi::class.java).fakeCall()
            }
            Assert.assertTrue(result.isFailure)
        }
    }
}