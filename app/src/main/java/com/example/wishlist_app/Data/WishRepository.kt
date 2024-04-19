package com.example.wishlist_app.Data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addWish(wish: Wish){
        wishDao.addwish(wish)
    }

    suspend fun UpdateWish(wish: Wish){
        wishDao.updatewish(wish)
    }

    suspend fun DeleteWish(wish: Wish){
        wishDao.deletewish(wish)
    }

    fun getWishes() : Flow<List<Wish>> = wishDao.getallwishes()

    fun getWishbyId(Id:Long):Flow<Wish>{
        return wishDao.getAWishbyId(Id)}

}