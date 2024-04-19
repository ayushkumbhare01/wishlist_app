package com.example.wishlist_app.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "wish_table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo(name ="wish-title")
    val title:String="",
    @ColumnInfo(name ="wish-desc")
    val description:String="")



object dummywish{
    val wishlist =
        listOf(
            Wish(title = "Google"
    , description = "hello from Google"),
        Wish(title = "Amazon"
            , description = "hello from amazon"),
        Wish(title = "Netflix"
            , description = "hello from netflix"),
        Wish(title = "Meta"
            , description = "hello from Meta"))
}
