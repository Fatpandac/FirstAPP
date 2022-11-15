package com.example.fistapp.example.refresh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fistapp.R
import com.example.fistapp.pojo.Product
import com.example.fistapp.server.Api
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class RefreshActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private var productList = ArrayList<Product>()
    private var idx: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refresh)
        val recyclerView = this.findViewById<RecyclerView>(R.id.refresh_content)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val recyclerViewAdapter = CustomAdapter(productList)
        recyclerView.adapter = recyclerViewAdapter
        val refreshLayout = findViewById<View>(R.id.refreshLayout) as RefreshLayout

        launch {
            recyclerViewAdapter.notifyItemChanged(loadNewProducts(idx++))
        }

        refreshLayout.setRefreshHeader(ClassicsHeader(this))
        refreshLayout.setRefreshFooter(ClassicsFooter(this))
        refreshLayout.setOnRefreshListener {
            launch {
                try {
                    idx = 1
                    recyclerViewAdapter.notifyItemChanged(loadNewProducts(idx++))
                    it.finishRefresh()
                } catch (E: Exception) {
                    it.finishLoadMore(false)
                }
            }
        }
        refreshLayout.setOnLoadMoreListener {
            launch {
                try {
                    recyclerViewAdapter.notifyItemChanged(loadNewProducts(idx++))
                    it.finishLoadMore(true)
                } catch (E: Exception) {
                    it.finishLoadMore(false)
                }
            }
        }
    }

    private suspend fun loadNewProducts(pageNum: Int): Int {
        val res = Api.retrofitService.getProducts(pageNum)
        val products = res.message.goods

        productList.addAll(products)

        return products.size
    }
}


class CustomAdapter(private val dataSet: ArrayList<Product>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.productTitle)
        val imageView: ImageView = view.findViewById(R.id.productImage)

        fun bind(product: Product) {
            textView.text = product.goodsName
            Glide.with(view).load(product.goodsBigLogo.replace("http://", "https://"))
                .placeholder(R.drawable.pic2).into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.product_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
