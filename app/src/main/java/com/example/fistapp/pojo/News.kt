package com.example.fistapp.pojo

import java.util.*

data class News(val img: Int, val title: String, val date: Date, val newIntent: () -> Unit)
