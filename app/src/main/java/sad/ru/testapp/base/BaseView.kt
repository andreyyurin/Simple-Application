package sad.ru.testapp.base

import android.view.View
import com.arellomobile.mvp.MvpView

interface BaseView : MvpView {
    fun stopLoading()
    fun showLoading()

    var viewSnackbar: View?
}