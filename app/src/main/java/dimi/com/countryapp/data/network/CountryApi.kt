package dimi.com.countryapp.data.network

import dimi.com.countryapp.data.dto.CountryDto
import retrofit2.Call
import retrofit2.http.GET

interface CountryApi {

    @GET("all")
    fun getAllCountries(): Call<List<CountryDto>>

    @GET("all")
    suspend fun getAllCountriesKoin(): List<CountryDto>
}