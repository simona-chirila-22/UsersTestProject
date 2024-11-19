package ro.simona.data.models

import com.google.gson.annotations.SerializedName

data class PictureDto(

    @SerializedName("large")
    val large: String,

    @SerializedName("medium")
    val medium: String,

    @SerializedName("thumbnail")
    val thumbnail: String
)
