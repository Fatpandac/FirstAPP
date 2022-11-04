package com.example.fistapp.example.fetchDataImageUrl

import com.example.fistapp.pojo.FetchNavigateItem
import com.example.fistapp.pojo.FetchViewPagerData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api-hmugo-web.itheima.net/api/public/v1/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("home/swiperdata")
    suspend fun getCarousel(): FetchViewPagerData

    @GET("home/catitems")
    suspend fun getNavigateItem(): FetchNavigateItem

}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
