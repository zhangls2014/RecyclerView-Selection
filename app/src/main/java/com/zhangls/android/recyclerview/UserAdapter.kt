package com.zhangls.android.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zhangls
 */
class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private val userList = arrayListOf<UserModel>()
    var selectionTracker: SelectionTracker<Long>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int = userList.size

    override fun getItemId(position: Int): Long {
        return userList[position].id
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(
            position + 1,
            userList[position],
            if (selectionTracker != null) selectionTracker!!.isSelected(userList[position].id) else false
        )
    }

    fun setUsers(users: List<UserModel>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }
}