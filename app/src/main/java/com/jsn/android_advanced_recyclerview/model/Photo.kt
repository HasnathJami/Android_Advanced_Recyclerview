package com.jsn.android_advanced_recyclerview.model

data class Photo(
    val id: String,
    val urls: Urls,
    val description: String?,
    val user: Customer
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

data class Customer(
    val name: String
)

