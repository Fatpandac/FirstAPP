package com.example.fistapp.pojo

import com.squareup.moshi.Json

data class FetchData(
    val message: List<FetchImage>,
    val meta: FetchMeta
)

data class FetchMeta(
    val msg: String,
    val status: String
)

data class FetchImage(
    @Json(name = "image_src") val img: String,
    @Json(name = "goods_id") val goodsID: Int
)