package dimi.com.countryapp

import android.app.Application
import dimi.com.countryapp.data.network.networkModule
import dimi.com.countryapp.di.AppComponent
import dimi.com.countryapp.di.DaggerAppComponent
import dimi.com.countryapp.di.MainModule
import dimi.com.countryapp.domain.countryModule
import dimi.com.countryapp.ui.fragment.fragmentModule
import dimi.com.countryapp.ui.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CountryApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initKoin()
    }

    private fun initKoin() {
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@CountryApp)
            modules(listOf(networkModule, fragmentModule, viewModelModule, countryModule))
        }
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }
}