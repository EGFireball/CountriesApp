package dimi.com.countryapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import dimi.com.countryapp.domain.Country
import dimi.com.countryapp.domain.CountryRepository
import javax.inject.Inject

class MainViewModel(application: Application): AndroidViewModel(application) {

    @Inject
    lateinit var countryRepository: CountryRepository

    fun getAllCountries(): LiveData<List<Country>> {
        return countryRepository.getCountries()
    }
}