package sad.ru.testapp.flow.users.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

internal class UsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val username: TextView = view.textName
    val createdAt: TextView = view.textCreatedAt
    val email: TextView = view.textEmail
    val layout: ConstraintLayout = view.layout
}