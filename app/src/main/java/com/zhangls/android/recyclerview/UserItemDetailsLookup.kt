package com.zhangls.android.recyclerview

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zhangls
 */
class UserItemDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        recyclerView.findChildViewUnder(e.x, e.y)?.let {
            val holder = recyclerView.getChildViewHolder(it)
            if (holder is UserViewHolder) {
                return holder.getItemDetails()
            }
        }

        return null
    }
}