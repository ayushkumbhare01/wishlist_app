package com.example.wishlist_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlist_app.Data.Wish
import com.example.wishlist_app.Data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewmodel(private val wishRepository: WishRepository=Graph.wishRepository):ViewModel() {

    var wishTitleState by mutableStateOf("")
    var wishdescriptionState by mutableStateOf("")

    fun onWishTitlechanged(newString:String){
        wishTitleState = newString
    }

    fun onwishDescriptionChanged(newString: String){
        wishdescriptionState = newString
    }

    lateinit var getAllWishes :Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getWishes()
        }
    }

    fun Addwish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
           wishRepository.addWish(wish)
        }
    }

    fun getAWishById(id:Long):Flow<Wish>{
        return wishRepository.getWishbyId(id)
    }

    fun UpdateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.UpdateWish(wish)
        }
    }
    fun Deletewish(wish: Wish){
        viewModelScope.launch(Dispatchers. IO){
            wishRepository.DeleteWish(wish)
        }
    }







}