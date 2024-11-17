package ro.simona.userstestproject

import org.koin.dsl.module

object AppModules {

    private val viewModels = module {

    }

    private val useCases = module {

    }

    val modules = listOf(
        viewModels, useCases
    )

}