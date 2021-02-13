package sad.ru.testapp.flow.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_users.*
import sad.ru.testapp.R
import sad.ru.testapp.base.BaseActivity
import sad.ru.testapp.flow.users.adapter.UsersAdapter
import sad.ru.testapp.model.User
import sad.ru.testapp.model.Users

class UsersActivity : BaseActivity<UsersView, UsersPresenter>(), UsersView {
    override fun layoutId(): Int = R.layout.activity_users

    override var presenter: UsersPresenter = UsersPresenter()

    override var viewSnackbar: View? = null

    private val adapter: UsersAdapter by lazy {
        UsersAdapter(this, presenter::removeUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewSnackbar = mainLayout

        initRecyclerView()
        bindButtons()
        loadUsers()
    }

    override fun showUsers(users: Users<List<User>>) {
        adapter.swapItems(users.data)
    }

    override fun addUser(users: Users<User>) {
        adapter.addItem(users.data)
    }

    override fun removeUser(users: Users<User>) {

    }

    override fun showLoading() {
        progressBar.isVisible = true
    }

    override fun stopLoading() {
        progressBar.isGone = true
    }

    private fun initRecyclerView() {
        recyclerUsers.layoutManager = LinearLayoutManager(this)
        recyclerUsers.adapter = adapter
    }

    private fun loadUsers() {
        presenter.loadUsers()
    }

    private fun bindButtons() {
        btnAddUser.setOnClickListener {
            bindDialog()
        }
    }

    private fun bindDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null)
        val emailText = dialogView.findViewById<AppCompatEditText>(R.id.textDiaEmail)
        val nameText = dialogView.findViewById<AppCompatEditText>(R.id.textDiaName)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.add_user_dialog_title)
            .setView(dialogView)
            .setNegativeButton(R.string.add_user_dialog_no) { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            .setPositiveButton(R.string.add_user_dialog_yes) { dialogInterface, i ->
                presenter.addUser(nameText.text.toString(), emailText.text.toString())
            }.show()
    }
}