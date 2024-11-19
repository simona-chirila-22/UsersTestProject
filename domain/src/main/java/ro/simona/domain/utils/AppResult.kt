package ro.simona.domain.utils

sealed class AppResult<out R> {
    data class Success<T>(val data: T) : AppResult<T>()
    data class Failure(val exception: Exception) : AppResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data = $data]"
            is Failure -> "Failure[exception = $exception]"
        }
    }
}