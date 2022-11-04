package com.example.fistapp.example.viewPager

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.fistapp.R
import com.example.fistapp.pojo.Image

class ViewPagerActivity : AppCompatActivity() {

    private val pageList = ArrayList<ImageView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        val viewPager = findViewById<ViewPager>(R.id.pager)
        initPageList()
        viewPager.adapter = MyPageAdapter(pageList)
    }

    private fun initPageList() {
        val picList = listOf(
            Image(R.drawable.pic1, "A building"),
            Image(R.drawable.pic2, "Rick"),
            Image(R.drawable.pic3, "A tree")
        )

        repeat(2) {
            for (pic in picList) {
                val imageView =
                    View.inflate(baseContext, R.layout.view_pager_item, null) as ImageView
                imageView.setImageResource(pic.image)
                imageView.contentDescription = pic.desc
                pageList.add(imageView)
            }
        }
    }
}

class MyPageAdapter(private val pageList: ArrayList<ImageView>) : PagerAdapter() {

    override fun getCount(): Int {
        return pageList.size
    }

    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view == ob
    }

    override fun destroyItem(container: ViewGroup, position: Int, ob: Any) {
        container.removeView(pageList[position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(pageList[position])
        return pageList[position]
    }

}
