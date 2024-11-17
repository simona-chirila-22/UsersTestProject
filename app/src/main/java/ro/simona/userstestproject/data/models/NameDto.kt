package ro.simona.userstestproject.data.models

import com.google.gson.annotations.SerializedName

data class NameDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String,
)