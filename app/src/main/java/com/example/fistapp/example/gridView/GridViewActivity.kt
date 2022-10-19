package com.example.fistapp.example.gridView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.fistapp.R

class GridViewActivity : AppCompatActivity() {

    private val imageGridList = ArrayList<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)
        val gridView = findViewById<GridView>(R.id.gridView)

        initImageGridList()

        gridView.adapter = GridViewAdapter(this, R.layout.grid_view_item, imageGridList)
        gridView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ShowImageActivity::class.java)
            val image = imageGridList[position]

            intent.putExtra("image", image.image)
            intent.putExtra("desc", image.desc)

            startActivity(intent)
        }
    }

    private fun initImageGridList() {
        repeat(10) {
            imageGridList.addAll(
                listOf(
                    Image(R.drawable.pic1, "A building"),
                    Image(R.drawable.pic2, "Rick"),
                    Image(R.drawable.pic3, "A view")
                )
            )
        }
    }

}

class Image(val image: Int, val desc: String)

class GridViewAdapter(activity: GridViewActivity, private val resourceId: Int, data: List<Image>) :
    ArrayAdapter<Image>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val newsImage: ImageView = view.findViewById(R.id.imageView)
        val image = getItem(position) ?: return view

        newsImage.setImageResource(image.image)
        newsImage.contentDescription = image.desc

        return view
    }

}