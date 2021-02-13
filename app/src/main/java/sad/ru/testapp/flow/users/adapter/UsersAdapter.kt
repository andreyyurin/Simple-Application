package sad.ru.testapp.flow.users.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import sad.ru.testapp.R
import sad.ru.testapp.model.User

internal class UsersAdapter(
    private val context: Context,
    private val removeUserFunc: (Int) -> Unit
) :
    RecyclerView.Adapter<UsersViewHolder>() {

    private var users = ArrayList<User>()

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        setData(holder, position)
        bindListeners(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    private fun setData(holder: UsersViewHolder, position: Int) {
        holder.email.text = users[position].email
        holder.createdAt.text = "Created at: ${users[position].convertedCreatedAt()}"
        holder.username.text = users[position].name
    }

    private fun bindListeners(holder: UsersViewHolder, position: Int) {
        holder.layout.setOnLongClickListener {
            MaterialAlertDialogBuilder(context)
                .setTitle(R.string.remove_user_dialog_title)
                .setNegativeButton(R.string.remove_user_dialog_no) { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .setPositiveButton(R.string.remove_user_dialog_yes) { dialogInterface, i ->
                    removeUser(position)
                }.show()

            return@setOnLongClickListener true
        }
    }

    private fun removeUser(position: Int) {
        removeUserFunc.invoke(users[position].id!!)
        users.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, users.size)
    }

    fun swapItems(items: List<User>) {
        this.users.clear()
        this.users.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: User) {
        this.users.add(item)
        notifyItemInserted(this.users.size)
    }
}