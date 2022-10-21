package com.example.fistapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.example.gridView.GridViewActivity
import com.example.fistapp.example.listView.NewsListActivity
import com.example.fistapp.example.viewPager.ViewPagerActivity

class MainActivity : AppCompatActivity() {

    private val menuList = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listview = findViewById<ListView>(R.id.list_view)
        initMenuList()
        listview.adapter = MenuListAdapter(this, R.layout.menu_item, menuList)
        listview.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, menuList[position].activity::class.java)
            startActivity(intent)
        }
    }

    private fun initMenuList() {
        menuList.addAll(
            listOf(
                Menu("ListView", NewsListActivity()),
                Menu("GridView", GridViewActivity()),
                Menu("ViewPager", ViewPagerActivity())
            )
        )
    }
}


class Menu(val title: String, val activity: AppCompatActivity)

class MenuListAdapter(activity: MainActivity, private val resourceId: Int, data: ArrayList<Menu>) :
    ArrayAdapter<Menu>(activity, resourceId, data) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val textView: TextView = view.findViewById(R.id.title_text)
        val menuItem = getItem(position) ?: return view

        textView.text = menuItem.title

        return view
    }
}
