package dimi.com.countryapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import dimi.com.countryapp.data.dto.CountryDto
import dimi.com.countryapp.domain.Country
import dimi.com.countryapp.domain.CountryRepositoryKoin
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModelKoin(get()) }
}

class MainViewModelKoin(private val repo: CountryRepositoryKoin) : ViewModel() {

//    private val countryDto: LiveData<List<CountryDto>> = liveData {
//        emit(repo.getCountries())
//    }

    val countries: LiveData<List<Country>> = liveData {
        emit(repo.getCountries().map { dto -> Country(dto) }.toList())
    }

}