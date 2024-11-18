package ro.simona.userstestproject.data.models

import com.google.gson.annotations.SerializedName

data class LocationDto(

    @SerializedName("street")
    val street: StreetDto,

    @SerializedName("city")
    val city: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("postcode")
    val postcode: String,

    @SerializedName("coordinates")
    val coordinates: CoordinatesDto,

    @SerializedName("timezone")
    val timezone: TimeZoneDto,
)

data class StreetDto(

    @SerializedName("number")
    val number: Int,

    @SerializedName("name")
    val name: String,
)

data class CoordinatesDto(

    @SerializedName("latitude")
    val latitude: String,

    @SerializedName("longitude")
    val longitude: String,
)

data class TimeZoneDto(

    @SerializedName("offset")
    val offset: String,

    @SerializedName("description")
    val description: String,
)
