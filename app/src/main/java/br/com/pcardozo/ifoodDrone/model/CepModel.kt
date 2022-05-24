package br.com.pcardozo.ifoodDrone.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CepModel(
    val bairro: String? = "",
    val cep: String? = "",
    val cidade: String? = "",
    val codigoIBGE: String? = "",
    val complemento: String? = "",
    @SerializedName("logradouro")
    val endereco: String? = "",
    val subTipoCep: String? = "",
    val tipoCep: String? = "",
    val uf: String? = ""
): Parcelable