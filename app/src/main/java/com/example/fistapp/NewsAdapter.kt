package com.example.fistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class NewsAdapter(activity: NewsActivity, private val resourceId: Int, data: List<News>) :
    ArrayAdapter<News>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
        val newsTittle: TextView = view.findViewById(R.id.newsTitle)
        val newsDate: TextView = view.findViewById(R.id.newsDate)
        val news = getItem(position)
        if (news != null) {
            newsImage.setImageResource(news.img)
            newsTittle.text = news.title
            newsDate.text = news.date.toString()
        }
        return view
    }
}
