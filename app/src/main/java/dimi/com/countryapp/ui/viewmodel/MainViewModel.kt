package dimi.com.countryapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dimi.com.countryapp.domain.CountryRepository
import dimi.com.countryapp.domain.Country
import dimi.com.countryapp.ui.viewobject.CountryVo
import javax.inject.Inject

class MainViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var countryRepository: CountryRepository

    private var countriesVoLd = MutableLiveData<List<CountryVo>>()

    fun getAllCountries(): LiveData<List<Country>> {

//        countriesVoLd.value = listOf()
//        if (countriesVoLd.value.isNullOrEmpty()) {
//            val countriesLd = countryRepository.countries
//            if (countriesLd.value.isNullOrEmpty()) {
//                countriesVoLd.value = countriesLd.value?.map { country -> CountryVo(country) }?.toList()
//            }
//        }
        return countryRepository.getCountries()
    }
}