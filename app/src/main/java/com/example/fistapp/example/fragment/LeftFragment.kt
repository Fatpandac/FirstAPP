package com.example.fistapp.example.fragment

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

class LeftFragment : ListFragment() {
    private val arrayList = ArrayList<String>()
    private var callbacks: Callbacks? = null

    internal interface Callbacks {
        fun send(s: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = activity as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        for (i in 1..50) {
            arrayList.add("第" + i + "条数据")
        }
        val arrayAdapter = ArrayAdapter(requireActivity(), R.layout.simple_list_item_1, arrayList)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        Log.e("tag", arrayList[position])
        callbacks!!.send(arrayList[position])
    }
}

