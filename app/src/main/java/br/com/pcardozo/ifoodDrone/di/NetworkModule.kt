package br.com.pcardozo.ifoodDrone.di


import br.com.pcardozo.ifoodDrone.domain.CepRepository
import br.com.pcardozo.ifoodDrone.domain.CepRepositoryImplements
import br.com.pcardozo.ifoodDrone.domain.CepService
import br.com.pcardozo.ifoodDrone.domain.CepUseCase
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val url = "https://viacep.com.br"

val NetworkModule = module {
    single { createService(get()) }
    single { createRetrofit(get(), url) }
    single { createOkHttpClient() }
}

fun createOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    return builder.build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(Gson().newBuilder().create()))
        .build()
}

fun createService(retrofit: Retrofit): CepService {
    return retrofit.create(CepService::class.java)
}

fun createTopJavaRepository(apiService: CepService): CepRepository {
    return CepRepositoryImplements(apiService)
}

fun createGetTopJavaUseCase(mainRepository: CepRepository): CepUseCase {
    return CepUseCase(mainRepository)
}