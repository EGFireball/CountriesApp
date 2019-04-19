package dimi.com.countryapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dimi.com.countryapp.data.network.CountryApi
import dimi.com.countryapp.data.dto.CountryDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countryApi: CountryApi) {

    fun getCountries(): LiveData<List<Country>> {
        val countries: MutableLiveData<List<Country>> = MutableLiveData()
        val call = countryApi.getAllCountries()
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
                    Log.e("CALL", "NOO")
                }
            }

            override fun onFailure(call: Call<List<CountryDto>>, t: Throwable) {
                countries.value = listOf()
                Log.e("CALL", "DAMNIT")
            }
        })
        return countries
    }
}