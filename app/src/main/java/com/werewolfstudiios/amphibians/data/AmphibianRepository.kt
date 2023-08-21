package com.werewolfstudiios.amphibians.data

import com.werewolfstudiios.amphibians.network.Amphibian
import com.werewolfstudiios.amphibians.network.AmphibianApiService

interface AmphibianRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibianRepository(private val amphibianApiService: AmphibianApiService) : AmphibianRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibianApiService.getAmphibians()
}
