package com.camihruiz24.amphibians_app.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("type")
    val type: String,
    @SerialName("img_src")
    val imgSrc: String,
)