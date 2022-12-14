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
import com.example.fistapp.example.broadcast.TimerActivity
import com.example.fistapp.example.fetchDataImageUrl.FetchDataImageURLActivity
import com.example.fistapp.example.fragment.MyFragmentActivity
import com.example.fistapp.example.gridView.GridViewActivity
import com.example.fistapp.example.listView.NewsListActivity
import com.example.fistapp.example.loginView.LoginActivity
import com.example.fistapp.example.refresh.RefreshActivity
import com.example.fistapp.example.services.PlayAudioActivity
import com.example.fistapp.example.viewPager.ViewPagerActivity
import com.example.fistapp.pojo.Menu

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
                Menu(R.string.viewIcon, "Login", LoginActivity()),
                Menu(R.string.listIcon, "ListView", NewsListActivity()),
                Menu(R.string.gridIcon, "GridView", GridViewActivity()),
                Menu(R.string.cardsIcon, "ViewPager", ViewPagerActivity()),
                Menu(
                    R.string.downloadIcon,
                    "Fetch Data and Show Images by URL",
                    FetchDataImageURLActivity()
                ),
                Menu(R.string.fragmentIcon, "Fragment", MyFragmentActivity()),
                Menu(R.string.refresh, "Refresh", RefreshActivity()),
                Menu(R.string.playIcon, "Play Music", PlayAudioActivity()),
                Menu(R.string.timerIcon, "Timer Broadcast", TimerActivity()),
            )
        )
    }
}

class MenuListAdapter(activity: MainActivity, private val resourceId: Int, data: ArrayList<Menu>) :
    ArrayAdapter<Menu>(activity, resourceId, data) {
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val menuIcon = view.findViewById<TextView>(R.id.menu_icon)
        val textView: TextView = view.findViewById(R.id.title_text)
        val menuItem = getItem(position) ?: return view

        menuIcon.setText(menuItem.icon)
        textView.text = menuItem.title

        return view
    }
}
