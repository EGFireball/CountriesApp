package dimi.com.countryapp.domain

import androidx.lifecycle.MutableLiveData
import dimi.com.countryapp.data.dto.CountryDto
import dimi.com.countryapp.data.network.CountryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countryApi: CountryApi) {

    fun getCountries(): MutableLiveData<List<Country>> {
        val countries: MutableLiveData<List<Country>> = MutableLiveData()
        val call = countryApi.getAllCountries()

        CoroutineScope(Dispatchers.IO).launch {
            call.enqueue(object : Callback<List<CountryDto>> {

                override fun onResponse(call: Call<List<CountryDto>>, response: Response<List<CountryDto>>) {
                    if (response.isSuccessful) {
                        if (response.body().isNullOrEmpty()) {
                            countries.value = listOf()
                        } else {
                            countries.value = response.body()?.map { countryDto -> Country(countryDto) }?.toList()
                        }
                    } else {
                        countries.value = listOf()
                    }
                }

                override fun onFailure(call: Call<List<CountryDto>>, t: Throwable) {
                    countries.value = listOf()
                }
            })
        }
        return countries
    }
}