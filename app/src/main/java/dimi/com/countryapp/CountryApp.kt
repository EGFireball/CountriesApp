package dimi.com.countryapp

import android.app.Application
import dimi.com.countryapp.di.AppComponent
import dimi.com.countryapp.di.DaggerAppComponent
import dimi.com.countryapp.di.MainModule

class CountryApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }

}