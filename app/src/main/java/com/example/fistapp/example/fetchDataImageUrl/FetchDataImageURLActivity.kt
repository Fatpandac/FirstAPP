package com.example.fistapp.example.fetchDataImageUrl

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fistapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FetchDataImageURLActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_data_show_image_url)
        val loadingText = findViewById<TextView>(R.id.loading_status)
        loadingText.text = "Loading ..."
        loadImage()
    }

    private fun loadImage() {
        launch {
            try {
                val res = Api.retrofitService.getPhotos()
                for ((index, i) in listOf(
                    R.id.fetch_image_view1,
                    R.id.fetch_image_view2,
                    R.id.fetch_image_view3
                ).withIndex()) {
                    val imageView = findViewById<ImageView>(i)
                    Glide.with(this@FetchDataImageURLActivity).load(res.message[index].img)
                        .into(imageView)
                }
                val loadingText = findViewById<TextView>(R.id.loading_status)
                loadingText.text = "Done."
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.cancel()
    }
}