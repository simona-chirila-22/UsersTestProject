package ro.simona.domain.usecases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ro.simona.domain.entities.UserEntity
import ro.simona.domain.repository.UsersRepository
import ro.simona.domain.utils.AppResult
import ro.simona.domain.utils.UsersConfig

class UsersUseCase(private val repository: UsersRepository, private val config: UsersConfig) {

    companion object {
        private const val INITIAL_PAGE = 1
    }

    private val _data: MutableSharedFlow<AppResult<List<UserEntity>>> = MutableSharedFlow()
    val data: SharedFlow<AppResult<List<UserEntity>>> = _data

    private val userList: MutableList<UserEntity> = mutableListOf()

    private var currentPage = INITIAL_PAGE

    private val mutex = Mutex()

    init {
        fetchPage()
    }

    fun advance() {
        fetchPage()
    }

    private fun fetchPage() {
        CoroutineScope(Dispatchers.IO).launch {
            mutex.withLock {
                if (currentPage <= config.maxPages) {
                    when (val result =
                        repository.getUsers(
                            page = currentPage,
                            usersPerPage = config.usersPerPage
                        )) {
                        is AppResult.Failure -> {
                            _data.emit(result)
                        }

                        is AppResult.Success -> {
                            userList.addAll(result.data)
                            currentPage++
                            _data.emit(AppResult.Success(userList))
                        }
                    }
                }
            }
        }
    }
}