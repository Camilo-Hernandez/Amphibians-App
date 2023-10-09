package com.camihruiz24.amphibians_app.fake

import com.camihruiz24.amphibians_app.data.Amphibian
import com.camihruiz24.amphibians_app.rules.TestDispatcherRule
import com.camihruiz24.amphibians_app.ui.screens.HomeViewModel
import com.camihruiz24.amphibians_app.ui.screens.UiState
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import kotlin.test.assertEquals
import kotlin.test.Test
import kotlin.test.BeforeTest

class HomeViewModelTest {
    private lateinit var viewModelTest: HomeViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @BeforeTest
    fun setUp() {
        viewModelTest = HomeViewModel(FakeNetworkAmphibianRepository())
    }

    @Test
    fun `Verify the retrieval of info from remote service is correctly executed and the uiState is updated`() =
        runTest {
            val expectedState: UiState<List<Amphibian>> =
                UiState.Success(FakeDataSource.fakeAmphibiansInfo)
            val realState = viewModelTest.uiState
            assertEquals(expectedState, realState)
        }
}
