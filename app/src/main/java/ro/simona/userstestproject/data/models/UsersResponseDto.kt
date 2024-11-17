package ro.simona.userstestproject.data.models

import com.google.gson.annotations.SerializedName

data class UsersResponseDto(

    @SerializedName("results")
    val results: List<UserDto>? = null,

    @SerializedName("info")
    val info: InfoDto
)