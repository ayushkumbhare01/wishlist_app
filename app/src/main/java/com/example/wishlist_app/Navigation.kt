package com.example.wishlist_app

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun navigation(viewModel: WishViewmodel = viewModel(),
               navcontroller:NavHostController = rememberNavController()){

    NavHost(navController = navcontroller,
        startDestination = Screen.HomeScreen.route,
        enterTransition ={ slideInHorizontally(initialOffsetX ={1000}) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut()} ){
        composable(Screen.HomeScreen.route,){
            Homeview(navcontroller,viewModel)
        }
        composable(Screen.AddScreen.route+"/{id}", arguments = listOf(navArgument("id"){
            type = NavType.LongType
            defaultValue = 0L
            nullable=false
        })){
            entry->
            val id =if(entry.arguments != null) entry.arguments!!.getLong("id")else 0L
            AddEditDetailView(id = id, viewmodel = viewModel, navController = navcontroller)
        }
    }
    
}
