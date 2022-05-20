package br.com.pcardozo.ifoodDrone.domain

import br.com.pcardozo.ifoodDrone.model.CepModel

interface CepRepository {

    suspend fun getCep(cep: String) : CepModel
}