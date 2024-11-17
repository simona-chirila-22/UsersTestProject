package ro.simona.userstestproject.data.repositoryimpl

import ro.simona.userstestproject.data.api.ApiClient
import ro.simona.userstestproject.data.api.ApiService
import ro.simona.userstestproject.data.models.UserDto
import ro.simona.userstestproject.domain.UsersRepository

class UsersRepositoryImpl(private val apiClient: ApiClient): UsersRepository {

    override suspend fun getUsers(): List<UserDto> {
        return apiClient.apiService.getUsers()
            .body()?.results ?: emptyList()
    }
}