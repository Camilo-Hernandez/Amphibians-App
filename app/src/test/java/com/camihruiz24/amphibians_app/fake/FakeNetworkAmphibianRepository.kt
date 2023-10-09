package com.camihruiz24.amphibians_app.fake

import com.camihruiz24.amphibians_app.data.Amphibian
import com.camihruiz24.amphibians_app.data.AmphibianRepository
import java.io.IOException

class FakeNetworkAmphibianRepository : AmphibianRepository {
    override suspend fun getAmphibiansInfo(): Result<List<Amphibian>> =
        try {
            val response = FakeApiService().getInfo()
            // Aquí deberíamos mapearlo al modelo de dominio para luego encapsularlo en Result
            Result.success(response)
        } catch (e: IOException) {
            Result.failure(e)
        }
}
