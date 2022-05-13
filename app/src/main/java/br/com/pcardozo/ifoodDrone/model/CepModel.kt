package br.com.pcardozo.ifoodDrone.model

data class CepModel(
    val bairro: String,
    val cep: String,
    val cidade: String,
    val codigoIBGE: String,
    val complemento: String,
    val endereco: String,
    val subTipoCep: String,
    val tipoCep: String,
    val uf: String
)