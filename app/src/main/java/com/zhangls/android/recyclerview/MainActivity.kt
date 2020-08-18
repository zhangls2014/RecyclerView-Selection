package com.zhangls.android.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tracker: SelectionTracker<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val userAdapter = UserAdapter()
        val users = arrayListOf<UserModel>()
        for (i in 1..30) {
            val age = (18..30).random()
            val id = age * 1000L + i
            val name = "Nick Zhang no.$id"
            val user = UserModel(id, name, age, introduce = "大家好，我是 ${name}。")
            users.add(user)
        }
        with(recyclerview) {
            adapter = userAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            userAdapter.setUsers(users)
        }

        tracker = SelectionTracker.Builder<Long>(
            "user-selection",
            recyclerview,
            StableIdKeyProvider(recyclerview),
            UserItemDetailsLookup(recyclerview),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            // 允许多选，SelectionPredicates.createSelectSingleAnything() 指定单选
            SelectionPredicates.createSelectAnything()
        ).build()
        tracker.addObserver(
            object : SelectionTracker.SelectionObserver<Long>() {
                override fun onSelectionChanged() {
                    val size = tracker.selection.size()
                    println("===tracker: items selected count is $size===")
                }
            }
        )
        userAdapter.selectionTracker = tracker
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tracker.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tracker.onRestoreInstanceState(savedInstanceState)
    }
}