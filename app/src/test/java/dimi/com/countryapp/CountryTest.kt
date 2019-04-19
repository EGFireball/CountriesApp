package dimi.com.countryapp

import dimi.com.countryapp.data.dto.CountryDto
import dimi.com.countryapp.data.network.CountryApi
import dimi.com.countryapp.domain.CountryRepository
import io.reactivex.observers.TestObserver
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


@RunWith(JUnit4::class)
class CountryTest {

    private val server: MockWebServer = MockWebServer()
    lateinit var repository: CountryRepository
    private val baseUrl = "https://restcountries.eu/rest/v2/"

    @Before
    fun setup() {
        server.start()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val countryApi: CountryApi = retrofit.create(CountryApi::class.java)

        repository = CountryRepository(countryApi)
    }

    @Test
    fun testGetCountries() {
        val testObserver = TestObserver<List<CountryDto>>()
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getJson("json/country.json"))
        )


    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}