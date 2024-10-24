package com.jsn.android_advanced_recyclerview.model

data class User(
    val name: String,
    val id: String
)

fun userList():List<User>{
    val users = mutableListOf<User>()
    users.add(User("Jami", "1244445"))
    users.add(User("Hasnath", "4555555"))
    users.add(User("Chowdhury", "9991199"))

    return users
}