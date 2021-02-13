package sad.ru.testapp.api

import io.reactivex.Observable
import retrofit2.http.*
import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

interface ApiServiceImpl {
    @GET("users")
    fun loadUsers(): Observable<Users<List<User>>>

    @GET("users")
    fun loadUsers(@Query("page") page: String): Observable<Users<List<User>>>

    @FormUrlEncoded
    @POST("users")
    fun addUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("status") status: String = "Active",
        @Field("gender") gender: String = "Male"
    ): Observable<Users<User>>

    @DELETE("users/{id}")
    fun removeUser(@Path("id") id: String): Observable<Users<User>>
}