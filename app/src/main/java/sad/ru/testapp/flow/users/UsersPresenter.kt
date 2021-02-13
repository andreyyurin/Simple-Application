package sad.ru.testapp.flow.users

import io.reactivex.functions.Consumer
import sad.ru.testapp.base.BasePresenter
import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

class UsersPresenter : BasePresenter<UsersView>(), UsersPresenterImpl {

    private val resultDataUsers = Consumer<Users<List<User>>> {
        checkCodeList(::loadUsersByPage, it)
    }

    private val resultDataUsersByPage = Consumer<Users<List<User>>> {
        mainView?.stopLoading()
        checkCodeList(mainView!!::showUsers, it)
    }

    private val resultDataAddUser = Consumer<Users<User>> {
        mainView?.stopLoading()
        checkCodeUser(mainView!!::addUser, it)
    }

    private val resultDataRemoveUser = Consumer<Users<User>> {
        mainView?.stopLoading()
        checkCodeUser(mainView!!::removeUser, it)
    }

    override fun loadUsers() {
        mainView?.showLoading()
        apiService
            .loadUsers()
            .subscribe(resultDataUsers, error)
    }

    override fun removeUser(id: Int) {
        mainView?.showLoading()
        apiService
            .removeUser(id.toString())
            .subscribe(resultDataRemoveUser, error)
    }

    override fun addUser(name: String, email: String) {
        mainView?.showLoading()
        apiService
            .addUser(name, email)
            .subscribe(resultDataAddUser, error)
    }

    override fun loadUsersByPage(data: Users<List<User>>) {
        apiService
            .loadUsers(data.meta?.pagination?.pages.toString())
            .subscribe(resultDataUsersByPage, error)
    }

}