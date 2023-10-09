package com.camihruiz24.amphibians_app.fake

import com.camihruiz24.amphibians_app.data.Amphibian
import com.camihruiz24.amphibians_app.data.AmphibiansService

class FakeApiService : AmphibiansService {
    override suspend fun getInfo(): List<Amphibian> = FakeDataSource.fakeAmphibiansInfo
}
