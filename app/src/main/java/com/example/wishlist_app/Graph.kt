package com.example.wishlist_app

import android.content.Context
import androidx.room.Room
import com.example.wishlist_app.Data.WishDatabase
import com.example.wishlist_app.Data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy { WishRepository(wishDao = database.wishDao()) }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDatabase::class.java,"WishListDB").build()
    }
}
