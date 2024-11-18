package ro.simona.userstestproject.domain

data class UsersConfig(
    val usersPerPage: Int = 20,
    val maxPages: Int = 3
)

