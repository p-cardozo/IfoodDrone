package br.com.pcardozo.ifoodDrone.domain.usecase

import br.com.pcardozo.ifoodDrone.model.ApiError


interface UseCaseResponse<T> {

    fun onSuccess(result: T)

    fun onError(apiError: ApiError?)
}

