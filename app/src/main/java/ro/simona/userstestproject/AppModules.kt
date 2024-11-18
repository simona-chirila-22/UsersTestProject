package ro.simona.userstestproject

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.simona.userstestproject.data.api.ApiService
import ro.simona.userstestproject.data.repositoryimpl.UsersRepositoryImpl
import ro.simona.userstestproject.domain.UsersConfig
import ro.simona.userstestproject.domain.UsersRepository
import ro.simona.userstestproject.domain.UsersUseCase
import ro.simona.userstestproject.ui.viewmodels.UsersViewModel

object AppModules {

    private val viewModels = module {
        viewModel { UsersViewModel(get()) }
    }

    private val useCases = module {
        factoryOf(::UsersUseCase)
    }

    private val repositories = module {
        single<UsersRepository> { UsersRepositoryImpl(get()) }
    }

    private val utils = module {
        single { UsersConfig(usersPerPage = 20, maxPages = 3) }
    }

    private val network = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        single<ApiService> { get<Retrofit>().create(ApiService::class.java) }
    }

    val modules = listOf(
        viewModels, repositories, network, useCases, utils
    )

}