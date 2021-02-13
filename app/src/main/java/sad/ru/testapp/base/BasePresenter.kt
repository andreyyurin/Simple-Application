package sad.ru.testapp.base

import android.util.Log
import com.google.android.material.snackbar.Snackbar
import io.reactivex.functions.Consumer
import sad.ru.testapp.api.ApiService
import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

abstract class BasePresenter<View : BaseView> : BasePresenterImpl<View> {

    private val codes = listOf(200, 201, 204)
    protected var mainView: View? = null
    protected val apiService = ApiService()

    protected val error = Consumer<Throwable> {
        Log.e("error-loading", "error ${it.message}")
        showSnackbar(it.message!!)
        mainView?.stopLoading()
    }

    override fun attachView(view: View) {
        mainView = view
    }

    override fun detachView() {
        mainView = null
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
    }

    override fun showSnackbar(error: String) {
        if (mainView?.viewSnackbar != null) {
            Snackbar.make(mainView?.viewSnackbar!!, error, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun checkCodeList(func: (Users<List<User>>) -> Unit, data: Users<List<User>>) {
        if (data.code in codes) {
            func.invoke(data)
            showSnackbar("Completed")
        } else {
            showSnackbar(data.data[0].message ?: "Something went wrong")
        }
    }

    override fun checkCodeUser(func: (Users<User>) -> Unit, data: Users<User>) {
        if (data.code in codes) {
            func.invoke(data)
            showSnackbar("Completed")
        } else {
            showSnackbar("Something went wrong")
        }
    }
}