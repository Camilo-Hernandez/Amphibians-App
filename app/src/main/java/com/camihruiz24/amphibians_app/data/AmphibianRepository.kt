package com.camihruiz24.amphibians_app.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

fun interface AmphibianRepository {
    suspend fun getAmphibiansInfo(): Result<List<Amphibian>>
}

class NetworkAmphibianRepository @Inject constructor(
    private val service: AmphibiansService
) : AmphibianRepository {
    override suspend fun getAmphibiansInfo(): Result<List<Amphibian>> =
        try {
            Result.success(service.getInfo())
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: coil.network.HttpException) {
            Result.failure(e)
        }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAmphibianRepository(
        amphibianRepositoryImpl: NetworkAmphibianRepository
    ): AmphibianRepository

}
