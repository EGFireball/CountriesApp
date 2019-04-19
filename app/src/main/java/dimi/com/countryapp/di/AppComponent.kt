package dimi.com.countryapp.di

import dagger.Component
import dimi.com.countryapp.CountryApp
import dimi.com.countryapp.MainActivity
import dimi.com.countryapp.ui.fragment.CountryListFragment
import dimi.com.countryapp.ui.viewmodel.MainViewModel
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(countryListFragment: CountryListFragment)

    fun inject(mainViewModel: MainViewModel)

    fun getApplication(): CountryApp

}