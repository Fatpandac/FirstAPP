package com.example.fistapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fistapp.server.Api
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class RefreshActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private var linearLayout: LinearLayout? = null
    private var idx: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh)
        linearLayout = this.findViewById(R.id.refresh_content)
        val refreshLayout = findViewById<View>(R.id.refreshLayout) as RefreshLayout

        launch {
            loadNewProducts(idx++)
        }

        refreshLayout.setRefreshHeader(ClassicsHeader(this))
        refreshLayout.setRefreshFooter(ClassicsFooter(this))
        refreshLayout.setOnRefreshListener {
            linearLayout?.removeAllViews()
            launch {
                try {
                    idx = 1
                    loadNewProducts(idx++)
                    it.finishRefresh()
                } catch (E: Exception) {
                    it.finishLoadMore(false)
                }
            }
        }
        refreshLayout.setOnLoadMoreListener {
            launch {
                try {
                    loadNewProducts(idx++)
                    it.finishLoadMore(true)
                } catch (E: Exception) {
                    it.finishLoadMore(false)
                }
            }
        }
    }

    private suspend fun loadNewProducts(pageNum: Int) {
        val res = Api.retrofitService.getProducts(pageNum)
        val products = res.message.goods
        for (product in products) {
            val itemLayout =
                LinearLayout.inflate(
                    this@RefreshActivity,
                    R.layout.product_item,
                    null
                ) as LinearLayout
            val imageView = itemLayout.findViewById<ImageView>(R.id.productImage)
            val titleView = itemLayout.findViewById<TextView>(R.id.productTitle)
            Glide.with(this@RefreshActivity)
                .load(product.goodsBigLogo.replace("http://", "https://"))
                .placeholder(R.drawable.pic2).into(imageView)
            titleView.text = product.goodsName
            linearLayout?.addView(itemLayout)
        }
    }
}