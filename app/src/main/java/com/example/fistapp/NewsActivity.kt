package com.example.fistapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class NewsActivity : AppCompatActivity() {
    private val news = ArrayList<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val listview = findViewById<ListView>(R.id.listView)
        initNews()
        val adapter = NewsAdapter(this, R.layout.news_item, news)
        listview.adapter = adapter
    }

    private fun initNews() {
        repeat(2) {
            for (i in 0..20) {
                news.add(News(R.drawable.icon, "Tittle 标题", Date()))
            }
        }
    }
}

