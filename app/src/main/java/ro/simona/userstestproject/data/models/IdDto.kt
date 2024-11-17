package ro.simona.userstestproject.data.models

import com.google.gson.annotations.SerializedName

data class IdDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("value")
    val value: String,
)
