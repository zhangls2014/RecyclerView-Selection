package com.zhangls.android.recyclerview

import androidx.recyclerview.selection.ItemDetailsLookup

/**
 * @author zhangls
 */
class UserItemDetails(private val position: Int, private val id: Long) : ItemDetailsLookup.ItemDetails<Long>() {
    override fun getSelectionKey(): Long? = id

    override fun getPosition(): Int = position
}