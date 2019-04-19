package dimi.com.countryapp.di

import dagger.Module
import dagger.Provides
import dimi.com.countryapp.CountryApp
import dimi.com.countryapp.data.network.CountryApi
import dimi.com.countryapp.domain.CountryRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainModule(private val app: CountryApp) {

    private val baseUrl = "https://restcountries.eu/rest/v2/"

    @Provides
    @Singleton
    fun getApplicationContext(): CountryApp = app

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiInterface(retroFit: Retrofit): CountryApi {
        return retroFit.create(CountryApi::class.java)
    }

    @Provides
    fun provideCountryRepository(countryApi: CountryApi) = CountryRepository(countryApi)
}