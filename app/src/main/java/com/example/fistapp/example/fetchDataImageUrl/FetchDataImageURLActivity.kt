package com.example.fistapp.example.fetchDataImageUrl

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.fistapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FetchDataImageURLActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private val carouselList = ArrayList<ImageView>()
    private val navigateList = ArrayList<ImageView>()
    private val floorViewList = ArrayList<LinearLayout>()
    private var screenWidth: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_data_show_image_url)
        val display = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(display)
        screenWidth = display.widthPixels

        val viewPager = findViewById<ViewPager>(R.id.fetch_view_pager)
        val navigator = findViewById<LinearLayout>(R.id.fetch_navigator)
        val floorLayout = findViewById<LinearLayout>(R.id.fetch_floor)

        launch {
            loadCarouselData()
            viewPager.adapter = MyPageAdapter(carouselList)
            loadNavigateData()
            for (item in navigateList) {
                navigator.addView(
                    item
                )
            }
            loadFloorData()
            for (item in floorViewList) {
                floorLayout.addView(
                    item
                )
            }
        }
    }

    private suspend fun loadCarouselData() {
        try {
            val res = Api.retrofitService.getCarousel()
            val picList = res.message
            for (pic in picList) {
                val imageView =
                    View.inflate(baseContext, R.layout.view_pager_item, null) as ImageView
                Glide.with(this@FetchDataImageURLActivity).load(pic.img).into(imageView)
                carouselList.add(imageView)
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

    private suspend fun loadNavigateData() {
        try {
            val res = Api.retrofitService.getNavigateItem()
            val picList = res.message
            for (pic in picList) {
                val imageView =
                    View.inflate(baseContext, R.layout.view_pager_item, null) as ImageView
                Glide.with(this@FetchDataImageURLActivity).load(pic.img).into(imageView)
                imageView.layoutParams = ViewGroup.LayoutParams(
                    screenWidth / picList.size,
                    screenWidth / picList.size
                )
                imageView.setPadding(5)
                navigateList.add(imageView)
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

    private suspend fun loadFloorData() {
        try {
            val imgIDList = listOf(R.id.img0, R.id.img1, R.id.img2, R.id.img3, R.id.img4)
            val res = Api.retrofitService.getFloorData()
            val floors = res.message
            for (floor in floors) {
                val floorView =
                    LinearLayout.inflate(baseContext, R.layout.floor_item, null) as LinearLayout
                val floorTitle = floorView.findViewById<ImageView>(R.id.floor_title)
                Glide.with(this).load(floor.floorTitle.img).into(floorTitle)
                for ((idx, product) in floor.productList.withIndex()) {
                    val productImg = floorView.findViewById<ImageView>(imgIDList[idx])
                    Glide.with(this).load(product.img).into(productImg)
                }
                floorViewList.add(floorView)
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.cancel()
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
