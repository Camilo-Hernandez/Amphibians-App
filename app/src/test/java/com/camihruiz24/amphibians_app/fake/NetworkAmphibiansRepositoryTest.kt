package com.camihruiz24.amphibians_app.fake

import com.camihruiz24.amphibians_app.data.Amphibian
import com.camihruiz24.amphibians_app.data.AmphibianRepository
import com.camihruiz24.amphibians_app.data.AmphibiansService
import com.camihruiz24.amphibians_app.data.NetworkAmphibianRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class NetworkAmphibiansRepositoryTest {

    private lateinit var fakeRepository: AmphibianRepository

    @BeforeTest
    fun setUp() {
        fakeRepository = NetworkAmphibianRepository(FakeApiService())
    }

    // Successful retrieval of amphibians' information
    @Test
    fun test_successful_retrieval_of_amphibians_information() {
        // Arrange
        val realRepository = NetworkAmphibianRepository(AmphibiansService.amphibiansService)

        // Act
        val result = runBlocking { realRepository.getAmphibiansInfo() }

        // Assert
        assertTrue(result.isSuccess)
        assertNotNull(result.getOrNull())
    }

    @Test
    fun `When calling the service, if successful, the service returns a Success Result of List of Amphibians`() =
        runTest {
            val expectedResult: Result<List<Amphibian>> =
                Result.success(FakeDataSource.fakeAmphibiansInfo)
            assertEquals(expectedResult, fakeRepository.getAmphibiansInfo())
        }

    @Test
    fun `If IOException occurs when calling the remote service, Result failure is returned from method`() =
        runTest {
            // TODO: Hacer test del camino de error
        }
}