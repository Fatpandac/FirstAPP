package com.example.fistapp.example.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.example.fistapp.pojo.CategoryChild
import com.example.fistapp.pojo.FetchCategory
import com.example.fistapp.server.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LeftFragment : ListFragment(), CoroutineScope by MainScope() {
    private val arrayList = ArrayList<String>()
    private var data = ArrayList<FetchCategory>()
    private var callbacks: Callbacks? = null

    internal interface Callbacks {
        fun send(s: List<CategoryChild>?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = activity as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            val res = Api.retrofitService.getCategories()
            data = res.message as ArrayList<FetchCategory>
            println(data)

            for (cate in data) {
                arrayList.add(cate.catName)
            }

            val arrayAdapter =
                ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, arrayList)
            listAdapter = arrayAdapter
            callbacks!!.send(data[0].children)
        }
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        callbacks!!.send(data[position].children)
    }
}

