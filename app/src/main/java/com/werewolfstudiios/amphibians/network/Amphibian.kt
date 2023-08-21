package com.werewolfstudiios.amphibians.network

import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val id: String,
    val name: String,
    val imgSrc: String,
    val description: String
)
