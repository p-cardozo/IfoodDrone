package br.com.pcardozo.ifoodDrone.di

import br.com.pcardozo.ifoodDrone.ui.cep.CepViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { CepViewModel(get()) }

    single { createGetTopJavaUseCase(get()) }

    single { createTopJavaRepository(get()) }
}