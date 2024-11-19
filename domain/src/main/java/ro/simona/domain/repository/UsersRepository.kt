package ro.simona.domain.repository

import ro.simona.domain.entities.UserEntity
import ro.simona.domain.utils.AppResult

interface UsersRepository {
    suspend fun getUsers(page: Int, usersPerPage: Int): AppResult<List<UserEntity>>
}