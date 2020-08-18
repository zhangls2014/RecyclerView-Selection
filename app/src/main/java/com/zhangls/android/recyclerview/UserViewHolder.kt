package com.zhangls.android.recyclerview

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textview.MaterialTextView

/**
 * @author zhangls
 */
class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val index = itemView.findViewById<MaterialTextView>(R.id.tvIndex)
    private val name = itemView.findViewById<MaterialTextView>(R.id.tvName)
    private val age = itemView.findViewById<MaterialTextView>(R.id.tvAge)
    private val introduce = itemView.findViewById<MaterialTextView>(R.id.tvIntroduce)
    private val checkBox = itemView.findViewById<MaterialCheckBox>(R.id.cbSelection)

    @SuppressLint("SetTextI18n")
    fun bindTo(position: Int, user: UserModel, isActivated: Boolean) {
        index.text = position.toString()
        name.text = user.name
        age.text = "${user.age} 岁"
        introduce.text = user.introduce
        checkBox.isChecked = isActivated
    }

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> = UserItemDetails(bindingAdapterPosition, itemId)
}