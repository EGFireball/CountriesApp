package dimi.com.countryapp.di.koin.demo

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class MySimplePresenter(val repo: HelloRepository) {

    fun sayHello() = "${repo.giveHello()} from $this"

    val appModule = module {

        // single instance of HelloRepository
        single<HelloRepository> { HelloRepositoryImpl() }

        // Simple Presenter Factory
        factory { MySimplePresenter(get()) }

        // View Model Factory
        viewModel { MyViewModel(get()) }
    }
}