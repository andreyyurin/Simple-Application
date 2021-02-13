package sad.ru.testapp.flow.users

import sad.ru.testapp.base.BaseView
import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

interface UsersView : BaseView {
    fun showUsers(users: Users<List<User>>)

    fun addUser(users: Users<User>)

    fun removeUser(users: Users<User>)
}