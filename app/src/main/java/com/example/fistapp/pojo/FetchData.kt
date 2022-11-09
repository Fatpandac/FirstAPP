package com.example.fistapp.pojo

import com.squareup.moshi.Json

data class FetchNavigateData(
    val message: List<NavigateItem>,
    val meta: FetchMeta,
)

data class FetchViewPagerData(
    val message: List<FetchImage>,
    val meta: FetchMeta
)

data class FetchFloorData(
    val message: List<FetchFloor>,
    val meta: FetchMeta
)

data class FetchCategories(
    val message: List<FetchCategory>,
    val meta: FetchMeta
)

data class FetchCategory(
    @Json(name = "cat_deleted") val catDeleted: Boolean,
    @Json(name = "cat_icon") val catIcon: String?,
    @Json(name = "cat_id") val catID: Int,
    @Json(name = "cat_level") val catLevel: Int,
    @Json(name = "cat_name") val catName: String,
    @Json(name = "cat_pid") val catPid: Int,
    val children: List<CategoryChild>
)

data class CategoryChild(
    @Json(name = "cat_deleted") val catDeleted: Boolean,
    @Json(name = "cat_icon") val catIcon: String?,
    @Json(name = "cat_id") val catID: Int,
    @Json(name = "cat_level") val catLevel: Int,
    @Json(name = "cat_name") val catName: String,
    @Json(name = "cat_pid") val catPid: Int,
    val children: List<CategoryItemChild>?,
)

data class CategoryItemChild(
    @Json(name = "cat_deleted") val catDeleted: Boolean,
    @Json(name = "cat_icon") val catIcon: String?,
    @Json(name = "cat_id") val catID: Int,
    @Json(name = "cat_level") val catLevel: Int,
    @Json(name = "cat_name") val catName: String,
    @Json(name = "cat_pid") val catPid: Int,
)

data class FetchFloor(
    @Json(name = "floor_title") val floorTitle: FloorTitle,
    @Json(name = "product_list") val productList: List<ProductList>,
)

data class ProductList(
    @Json(name = "image_src") val img: String,
    @Json(name = "image_width") val width: String,
    @Json(name = "navigator_url") val navigatorURL: String,
    @Json(name = "open_type") val openType: String,
    val name: String,
)

data class FloorTitle(
    @Json(name = "image_src") val img: String,
    val name: String,
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

