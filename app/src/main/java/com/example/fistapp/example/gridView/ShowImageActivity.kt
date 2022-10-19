package com.example.fistapp.example.gridView

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R

class ShowImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_image)
        val showImageView = findViewById<ImageView>(R.id.showImageView)
        val image = intent.getIntExtra("image", R.drawable.pic1)
        val desc = intent.getStringExtra("desc")

        showImageView.setImageResource(image)
        showImageView.contentDescription = desc
    }
}