package com.camihruiz24.amphibians_app.fake

import com.camihruiz24.amphibians_app.data.Amphibian

object FakeDataSource {
    private const val name1 = "id1"
    private const val description1 = "description1"
    private const val type1 = "type2"
    private const val url1 = "url1"

    private const val name2 = "id2"
    private const val description2 = "description2"
    private const val type2 = "type2"
    private const val url2 = "url2"

    val fakeAmphibiansInfo = listOf(
        Amphibian(name1, description1, type1, url1),
        Amphibian(name2, description2, type2, url2),
    )
}
