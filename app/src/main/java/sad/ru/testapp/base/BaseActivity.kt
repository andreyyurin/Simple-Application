package sad.ru.testapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<Vi : BaseView, T : BasePresenter<Vi>> : AppCompatActivity(), BaseView {

    protected abstract var presenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        presenter.attachView(this as Vi)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    protected

    abstract fun layoutId(): Int
}