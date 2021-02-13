package sad.ru.testapp.model

import java.text.SimpleDateFormat
import java.util.*

data class Users<T>(
    val code: Int,
    val data: T,
    val meta: Meta?
)

data class User(
    val field: String?,
    val message: String?,
    val created_at: String?,
    val email: String?,
    val gender: String?,
    val id: Int?,
    val name: String?,
    val status: String?,
    val updated_at: String?
) {
    fun convertedCreatedAt(): String {
        val pattern = "dd MMM yyyy HH:mm"
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.GERMANY);
        val date = inputFormat.parse(created_at)
        val sdf = SimpleDateFormat(pattern, Locale.GERMANY)
        return sdf.format(date)
    }
}

data class Meta(
    val pagination: Pagination
)

data class Pagination(
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)
