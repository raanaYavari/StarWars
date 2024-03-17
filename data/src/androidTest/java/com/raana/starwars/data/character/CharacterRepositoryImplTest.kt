package com.raana.starwars.data.character

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.raana.starwars.data.helper.createRetrofitObject
import com.raana.starwars.data.helper.readFileFromResources
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

/**
 * This test is instrumented because I wanted to showcase Instrument test on Repository
 * It will be use full if we have data store
 * For most of the Repository Impls, local tests are preferred
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {

    companion object {
        private val context: Context = ApplicationProvider.getApplicationContext()
        private val dataStore =
            PreferenceDataStoreFactory.create(
                scope = TestScope(UnconfinedTestDispatcher() + Job()),
                produceFile = { context.preferencesDataStoreFile("TEST_DATA_STORE") }
            )
    }

    @Test
    fun getCharacterList_isSuccessful_mustReturnSuccess() {
        runTest {
            val api =
                createRetrofitObject(GenerateGetCharacterListFakeInterceptor()).create(CharacterApi::class.java)
            val repository = CharacterRepositoryImpl(api)
            val result = repository.getCharacterList("")
            //Testing Response Json Values
            Assert.assertEquals("Luke Skywalker", result.getOrNull()?.get(0)?.name)
        }
    }
}

private class GenerateGetCharacterListFakeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = readFileFromResources(javaClass, "get_character_list_response.json")

        return Response.Builder()
            .code(HttpURLConnection.HTTP_OK)
            .message(response)
            .body(response.toResponseBody())
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .addHeader("content-type", "application/json")
            .build()
    }
}