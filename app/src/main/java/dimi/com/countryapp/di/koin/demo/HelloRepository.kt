package dimi.com.countryapp.di.koin.demo

interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello() = "Hello Koin"
}