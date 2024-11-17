package ro.simona.userstestproject.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ro.simona.userstestproject.data.models.UsersResponseDto

interface ApiService {

    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 20,
        @Query("seed") seed: String = "abc"
    ): Response<UsersResponseDto>
}