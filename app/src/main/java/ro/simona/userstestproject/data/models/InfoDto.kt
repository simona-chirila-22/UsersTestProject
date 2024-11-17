package ro.simona.userstestproject.data.models

import com.google.gson.annotations.SerializedName

data class InfoDto(

    @SerializedName("seed")
    val seed: String,

    @SerializedName("results")
    val results: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("version")
    val version: String,
)