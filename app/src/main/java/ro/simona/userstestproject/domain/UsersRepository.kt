package ro.simona.userstestproject.domain

interface UsersRepository {
    suspend fun getUsers(page: Int, usersPerPage: Int): AppResult<List<UserEntity>>
}