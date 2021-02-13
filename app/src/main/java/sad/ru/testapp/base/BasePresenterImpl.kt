package sad.ru.testapp.base

import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

interface BasePresenterImpl<in View : BaseView> {
    fun attachView(view: View)

    fun detachView()

    fun subscribe()

    fun unSubscribe()

    fun showSnackbar(error: String)

    fun checkCodeList(func: (Users<List<User>>) -> Unit, data: Users<List<User>>)

    fun checkCodeUser(func: (Users<User>) -> Unit, data: Users<User>)
}