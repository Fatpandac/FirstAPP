package com.example.fistapp.pojo

import com.squareup.moshi.Json

data class FetchNavigateItem(
    val message: List<NavigateItem>,
    val meta: FetchMeta,
)

data class FetchViewPagerData(
    val message: List<FetchImage>,
    val meta: FetchMeta
)

data class NavigateItem(
    val name: String,
    @Json(name = "image_src") val img: String,
)

data class FetchImage(
    @Json(name = "image_src") val img: String,
    @Json(name = "goods_id") val goodsID: Int
)

data class FetchMeta(
    val msg: String,
    val status: String
)

