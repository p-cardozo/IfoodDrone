package br.com.pcardozo.ifoodDrone.domain

import br.com.pcardozo.ifoodDrone.model.CepModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {
    @GET("/ws/{cep}/json/")
    suspend fun getPullRequest(
        @Path("cep") cep: String,
    ): CepModel
}