package com.raana.starwars.data.helper

import com.raana.starwars.data.base.response.ApiResponse
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

internal fun createRetrofit(webServer: MockWebServer): Retrofit = Retrofit.Builder()
    .baseUrl(webServer.url("/"))
    .client(
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.SECONDS)
            .build()
    ).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

fun MockResponse.setBodyFromFile(name: String): MockResponse {
    javaClass.classLoader?.getResourceAsStream(name)?.also {
        setBody(it.source().buffer().readString(StandardCharsets.UTF_8))
    }
    return this
}

interface FakeApi {
    @GET("/fake")
    suspend fun fakeCall(): ApiResponse<Unit>

}
