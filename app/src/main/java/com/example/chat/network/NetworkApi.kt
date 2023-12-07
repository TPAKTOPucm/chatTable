package com.example.chat.network

import android.os.Environment
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
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import java.io.File
import java.io.FileOutputStream
import java.io.Serializable

interface NetworkApi{
    suspend fun getCharacters(): Iterable<Character>
}
private const val BASE_URL = "www.anapioficeandfire.com"
const val NUMBER_AT_GROUP_LIST = 19
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

    override suspend fun getCharacters(): List<Character> {
        return try {
            client.get{
                url{
                    host = BASE_URL
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    path("api","characters")
                    formData {
                        parameter("page", NUMBER_AT_GROUP_LIST)
                        parameter("pagesize", 50)
                    }
                }
            }.let {
                Log.d("body", it.body())
                saveFile("data_${NUMBER_AT_GROUP_LIST}.txt", it.body<JsonArray>().toString().toByteArray())
                it.body()
            }
        } catch (ex: Exception) {
            Log.e("bad result(", ex.message.toString())
            listOf<Character>()
        }
    }
    fun saveFile(fileName: String, data: ByteArray){
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (downloadDir.canWrite()) {
            val dst = FileOutputStream(File(downloadDir, fileName))
            dst.write(data)
            dst.close()
        }
    }
}