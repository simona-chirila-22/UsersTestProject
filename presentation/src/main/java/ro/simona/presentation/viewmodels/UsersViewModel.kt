package ro.simona.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import ro.simona.domain.entities.UserEntity
import ro.simona.domain.usecases.UsersUseCase
import ro.simona.domain.utils.AppResult

class UsersViewModel(private val usersUseCase: UsersUseCase) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(UsersScreenState())
    val state = _state.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            usersUseCase.data.collectLatest { result ->
                when (result) {
                    is AppResult.Failure -> _state.update {
                        it.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = result.exception.message
                        )
                    }

                    is AppResult.Success -> _state.update {
                        it.copy(
                            isLoading = false,
                            isError = false,
                            errorMessage = null,
                            users = result.data
                        )
                    }
                }
            }
        }
    }

    fun shouldAdvance() = usersUseCase.advance()

    fun shouldRetry() = usersUseCase.advance()
}

data class UsersScreenState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val users: List<UserEntity> = emptyList()
)
