package sad.ru.testapp.flow.users

import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

interface UsersPresenterImpl {
    fun loadUsers()

    fun removeUser(id: Int)

    fun addUser(name: String, email: String)

    fun loadUsersByPage(data: Users<List<User>>)
}