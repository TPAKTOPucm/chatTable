package com.example.chat.network

import android.util.Log
import com.example.chat.models.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttp

interface NetworkApi{
    suspend fun getCharacters(): Iterable<Character>
}
private const val BASE_URL = "www.anapioficeandfire.com"
private const val MY_NUM = 12
class KtorNetwork : NetworkApi {
    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val client: HttpClient by lazy {
        HttpClient{
            install(ContentNegotiation){ json(json) }
            install(Logging){
                level = LogLevel.ALL
            }
        }
    }

    override suspend fun getCharacters(): Iterable<Character> {
        return try {
            client.get{
                url{
                    host = BASE_URL
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    path("api","characters")
                    formData {
                        parameter("page", MY_NUM)
                        parameter("pagesize", 50)
                    }
                }
            }.let<HttpResponse, Iterable<Character>> {
                //listOf()
                Log.d("body", it.body())
                it.body()
            }
        } catch (ex: Exception) {
            Log.e("bla", ex.message.toString())
            listOf<Character>()
        }
    }
}