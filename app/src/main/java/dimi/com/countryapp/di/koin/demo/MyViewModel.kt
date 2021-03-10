package dimi.com.countryapp.di.koin.demo

import androidx.lifecycle.ViewModel

class MyViewModel(val repo : HelloRepository) : ViewModel() {

    fun sayHello() = "${repo.giveHello()} from $this"
}