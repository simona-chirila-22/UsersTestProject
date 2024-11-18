package ro.simona.userstestproject.data.repositoryimpl

import ro.simona.userstestproject.data.api.ApiService
import ro.simona.userstestproject.data.models.toFullName
import ro.simona.userstestproject.domain.AppResult
import ro.simona.userstestproject.domain.UserEntity
import ro.simona.userstestproject.domain.UsersRepository

class UsersRepositoryImpl(private val apiService: ApiService) : UsersRepository {
    override suspend fun getUsers(page: Int, usersPerPage: Int): AppResult<List<UserEntity>> {
        val response = apiService.getUsers(page = page, results = usersPerPage)
        return if (response.isSuccessful) {
            val userList: MutableList<UserEntity> = mutableListOf()
            response.body()?.results?.forEach { user ->
                userList.add(
                    UserEntity(
                        name = user.name.toFullName(),
                        age = user.dob.age,
                        location = user.nat,
                        pictureUrl = user.picture.thumbnail,
                        date = user.registered.date
                    )
                )

            }
            AppResult.Success(userList)
        } else {
            AppResult.Failure(Exception(response.errorBody().toString()))
        }
    }


}