package com.example.fistapp.example.listView

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val pic = intent.getIntExtra("pic", 0)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val picView = findViewById<ImageView>(R.id.news_detail_pic)
        val titleTextView = findViewById<TextView>(R.id.news_detail_title)
        val contentTextView = findViewById<TextView>(R.id.news_detail_content)

        picView.setImageResource(pic)
        titleTextView.text = title
        contentTextView.text = content
    }
}
