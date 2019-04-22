package dimi.com.countryapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dimi.com.countryapp.domain.Country
import dimi.com.countryapp.domain.CountryRepository
import javax.inject.Inject

class MainViewModel: ViewModel() {

    @Inject
    lateinit var countryRepository: CountryRepository

    private var countriesLd: MutableLiveData<List<Country>>? = null

    fun getAllCountries(): LiveData<List<Country>> {
        if (countriesLd == null) {
            countriesLd = countryRepository.getCountries()
        }
        return countriesLd as LiveData<List<Country>>
    }

    fun refreshCountriesData(): LiveData<List<Country>> {
        countriesLd = null
        return getAllCountries()
    }
}