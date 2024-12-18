package ro.simona.data.models

import com.google.gson.annotations.SerializedName

data class NameDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String,
)

fun NameDto.toFullName(): String {
    return "$first $last"
}