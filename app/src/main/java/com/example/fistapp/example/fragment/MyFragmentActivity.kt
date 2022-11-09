package com.example.fistapp.example.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fistapp.R
import com.example.fistapp.pojo.CategoryChild
import com.google.android.flexbox.FlexboxLayout


class MyFragmentActivity : AppCompatActivity(), LeftFragment.Callbacks {

    private var contentLayout: LinearLayout? = null
    private var screenWidth = Resources.getSystem().displayMetrics.widthPixels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_fragment)
        contentLayout = findViewById(R.id.content_fragment)
    }

    override fun send(s: List<CategoryChild>?) {
        Log.e("S", s.toString())
        contentLayout?.removeAllViews()
        updateCateView(s)
    }

    private fun updateCateView(s: List<CategoryChild>?) {
        val nullParent: ViewGroup? = null

        for (cate in s!!) {
            val cateView = LayoutInflater.from(this).inflate(R.layout.cate_item, nullParent)
            val flexboxLayout = cateView.findViewById<FlexboxLayout>(R.id.flex)
            val titleView = cateView.findViewById<TextView>(R.id.tv)
            titleView.text = cate.catName
            for (item in cate.children!!) {
                val view: View =
                    LayoutInflater.from(this).inflate(R.layout.image_and_text, nullParent)
                val imageView = view.findViewById<ImageView>(R.id.item_img)
                val textView = view.findViewById<TextView>(R.id.item_text)
                val w: Int = 3 * screenWidth / 4 / 4
                val paramsView = LinearLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT)
                val paramsImg = LinearLayout.LayoutParams(w, w)
                val paramsTextView =
                    LinearLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT)

                view.layoutParams = paramsView
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                Glide.with(this).load(item.catIcon).into(imageView)
                imageView.layoutParams = paramsImg

                textView.text = item.catName
                textView.layoutParams = paramsTextView

                flexboxLayout.addView(view)
            }
            contentLayout?.addView(cateView)
        }
    }
}