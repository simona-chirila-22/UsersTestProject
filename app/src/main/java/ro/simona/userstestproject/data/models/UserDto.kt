package ro.simona.userstestproject.data.models

import com.google.gson.annotations.SerializedName

data class UserDto(

    @SerializedName("gender")
    val gender: String,

    @SerializedName("name")
    val name: NameDto,

    @SerializedName("location")
    val location: LocationDto,

    @SerializedName("email")
    val email: String,

    @SerializedName("login")
    val login: LoginDto,

    @SerializedName("dob")
    val dob: DobDto,

    @SerializedName("registered")
    val registered: RegisteredDto,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("cell")
    val cell: String,

    @SerializedName("id")
    val id: IdDto,

    @SerializedName("picture")
    val picture: PictureDto,

    @SerializedName("nat")
    val nat: String
)
