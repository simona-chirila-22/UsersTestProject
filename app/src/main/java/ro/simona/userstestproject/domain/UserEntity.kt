package ro.simona.userstestproject.domain

data class UserEntity(
    val name: String,
    val age: Int,
    val location: String,
    val pictureUrl: String,
    val date: String,
    val hasAttachment: Boolean = false,
    val isFavorite: Boolean = false,
)