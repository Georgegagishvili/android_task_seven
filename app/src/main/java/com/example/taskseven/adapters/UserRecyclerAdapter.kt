package com.example.taskseven.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskseven.R
import com.example.taskseven.activities.UserActivity
import com.example.taskseven.models.User
import com.squareup.picasso.Picasso

class UserRecyclerAdapter() :
    RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>() {

    private var users : List<User>  = emptyList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val picasso: Picasso = Picasso.get()

        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            picasso.load(user.avatar).placeholder(R.drawable.ic_launcher_background)
                .into(itemView.findViewById<ImageView>(R.id.userImageView))

            itemView.findViewById<TextView>(R.id.userName).text =
                "${user.firstName} ${user.lastName}"
            itemView.findViewById<TextView>(R.id.userEmail).text = user.email
            itemView.findViewById<TextView>(R.id.userId).text = "ID: ${user.id}"


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, UserActivity::class.java)
                intent.putExtra("userId", user.id)
                itemView.context.startActivity(intent)
            }
        }
    }


    override fun getItemCount(): Int {
        return users.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_card, parent, false)

        return ViewHolder(view)
    }

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

}