package com.example.wishlist_app.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class WishDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addwish(wishEntity: Wish)


    //Load all wishes from the wish table
    @Query("Select * from `wish_table`")
    abstract fun getallwishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updatewish(wishEntity: Wish)

    @Delete
    abstract suspend fun deletewish(wishEntity: Wish)

    @Query("Select * from `wish_table`where id=:id")
    abstract fun getAWishbyId(id:Long): Flow<Wish>





}