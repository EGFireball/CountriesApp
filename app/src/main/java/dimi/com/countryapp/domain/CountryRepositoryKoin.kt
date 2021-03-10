package dimi.com.countryapp.domain

import dimi.com.countryapp.data.network.CountryApi
import org.koin.dsl.module

val countryModule = module {
    factory { CountryRepositoryKoin(get()) }
}

class CountryRepositoryKoin(private val countryAPI: CountryApi) {
    suspend fun getCountries() = countryAPI.getAllCountriesKoin()
}