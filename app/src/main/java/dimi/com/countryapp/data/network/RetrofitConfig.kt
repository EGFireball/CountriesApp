package dimi.com.countryapp.data.network

import dimi.com.countryapp.di.MainModule_ProvideOkHttpClientFactory.provideOkHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideCountryApi(get()) }
    single { provideRetrofit(get()) }
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://restcountries.eu/rest/v2/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideCountryApi(retrofit: Retrofit): CountryApi = retrofit.create(CountryApi::class.java)

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()
        // DONT INCLUDE API KEYS IN YOUR SOURCE CODE
        //val url = req.url().newBuilder().addQueryParameter("APPID", "your_key_here").build()
        //req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}