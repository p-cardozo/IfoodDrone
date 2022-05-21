package br.com.pcardozo.ifoodDrone.domain

import br.com.pcardozo.ifoodDrone.model.CepModel

class CepRepositoryImplements(private val apiService: CepService) : CepRepository {

    override suspend fun getCep(cep: String): CepModel {
        return apiService.getPullRequest(cep)
    }
}