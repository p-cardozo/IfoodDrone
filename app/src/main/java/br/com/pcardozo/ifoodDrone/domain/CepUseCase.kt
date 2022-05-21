package br.com.pcardozo.ifoodDrone.domain

import br.com.pcardozo.ifoodDrone.domain.usecase.UseCase
import br.com.pcardozo.ifoodDrone.model.CepModel

class CepUseCase(private val cepRepository: CepRepository) : UseCase<CepModel, String>() {

    override suspend fun run(params: String?): CepModel {
        return cepRepository.getCep(params ?: "")
    }

}