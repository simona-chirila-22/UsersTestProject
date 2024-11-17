package ro.simona.userstestproject.domain

import ro.simona.userstestproject.data.models.UserDto

interface UsersRepository {

    suspend fun getUsers(): List<UserDto>
}