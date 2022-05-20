package br.com.pcardozo.ifoodDrone.ui.cep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pcardozo.ifoodDrone.domain.CepUseCase
import br.com.pcardozo.ifoodDrone.model.ApiError
import br.com.pcardozo.ifoodDrone.model.CepModel
import br.com.pcardozo.ifoodDrone.domain.usecase.UseCaseResponse
import kotlinx.coroutines.cancel

class CepViewModel(private val useCase: CepUseCase) : ViewModel() {

    private val state: MutableLiveData<CepDataStates> = MutableLiveData()
    val viewStates: LiveData<CepDataStates> = state

    fun interpret(interactor: CepInteractor) = when (interactor) {
        is CepInteractor.GetCep -> getListTopJava(interactor.cep)
    }

    private fun getListTopJava(cep: String) {
        useCase.invoke(viewModelScope, cep, object : UseCaseResponse<CepModel> {
            override fun onSuccess(result: CepModel) {
                state.postValue(CepDataStates.CallSucess(result))
            }

            override fun onError(apiError: ApiError?) {
                state.postValue(CepDataStates.CallError)
            }
        })
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}