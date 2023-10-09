package com.camihruiz24.amphibians_app.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Singleton

fun interface AmphibiansService {
    @GET("amphibians")
    suspend fun getInfo(): List<Amphibian>

    companion object {
        private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

        private val retrofitObject: Retrofit = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()

        val amphibiansService: AmphibiansService by lazy {
            retrofitObject.create(AmphibiansService::class.java)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides
    @Singleton
    fun provideAmphibianService(): AmphibiansService =
        AmphibiansService.amphibiansService
}
