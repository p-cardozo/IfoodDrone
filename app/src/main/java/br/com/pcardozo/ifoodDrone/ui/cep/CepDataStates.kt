package br.com.pcardozo.ifoodDrone.ui.cep

import br.com.pcardozo.ifoodDrone.model.CepModel


sealed class CepDataStates {
    data class CallSucess(val cep: CepModel) : CepDataStates()
    object CallError : CepDataStates()
}

sealed class CepInteractor {
    data class GetCep(val cep: String) : CepInteractor()
}