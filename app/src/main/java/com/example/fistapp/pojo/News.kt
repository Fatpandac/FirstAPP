package com.example.fistapp.pojo

import java.util.*

class News(val img: Int, val title: String, val date: Date, val newIntent: () -> Unit)
