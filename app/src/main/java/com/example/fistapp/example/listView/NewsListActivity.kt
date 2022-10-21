package com.example.fistapp.example.listView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R
import com.example.fistapp.pojo.News
import java.util.*

class NewsListActivity : AppCompatActivity() {
    private val news = ArrayList<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newslist)
        val listview = findViewById<ListView>(R.id.listView)
        initNews()
        val adapter = NewsListAdapter(this, R.layout.news_item, news)
        listview.adapter = adapter
    }

    private fun initNews() {
        repeat(2) {
            for (i in 0..20) {
                news.add(News(R.drawable.icon, "Tittle 标题", Date()) {
                    val newIntent = Intent(this, NewsActivity::class.java)
                    newIntent.putExtra("pic", R.drawable.img)
                    newIntent.putExtra("title", "Hello World")
                    newIntent.putExtra(
                        "content",
                        "It’s true, there are rare occasions where I shed a tear.  The last time was, I think, in the last minutes of the film ‘The Father’ with Anthony Hopkins.  But it was a minor tear, I would say, not a massive sob, almost decorative. And what I was responding to wasn’t real. It was just a representation of reality.\n" +
                                "\n" +
                                "And yet I have had many opportunities worthy of tears in the last twenty years or so.  My marriage has broken up and I have seen my children suffer because of it.  At my beloved father’s funeral I was dry eyed ( although I confess weeping as he faded on his hospital bed). I have lost friends and I have lost dreams.  When I fell in love with a woman and she abandoned me a few years back, not a single sob escape me.\n" +
                                "\n" +
                                "My tear ducts no longer seem to work unless there is a chopped onion nearby. I genuinely mourn this development.  It seems to represent a coarsening of my emotions, a toughening of my hide that in some senses is a relief, but in another sense is a real loss.\n" +
                                "\n" +
                                "This isn’t to say I don’t feel sad sometimes.  Of course I do.  But the release that used to come with a good cry seems no longer available to me."
                    )
                    startActivity(newIntent)
                })
            }
        }
    }
}

class NewsListAdapter(activity: NewsListActivity, private val resourceId: Int, data: List<News>) :
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
            view.setOnClickListener {
                news.newIntent()
            }
        }

        return view
    }

}

