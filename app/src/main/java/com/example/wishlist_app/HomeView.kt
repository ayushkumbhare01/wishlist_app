package com.example.wishlist_app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlist_app.Data.Wish


@Composable
fun Homeview(navController: NavController, viewmodel: WishViewmodel) {
    var confirmdel = remember{ mutableStateOf(false) }
    Scaffold(
        topBar = { AppBarView(Title = "Wishlist") {} },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddScreen.route + "/0L") },
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.LightGray,
                backgroundColor = Color(0xFF019DB3)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        
        val wishlist = viewmodel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            if(wishlist.value.isEmpty()){
                item{
                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                    Text(
                        text = "No wishes found", fontSize = 30.sp
                    )
                    Text(text = "Tap on the + sign to get started", fontWeight = FontWeight.SemiBold)}
                }
            }else{
            items(wishlist.value) { wish ->
                Spacer(modifier = Modifier.height(5.dp))
                WishItem(
                    wish = wish,
                    onclick = {
                        val id = wish.id
                        navController.navigate(Screen.AddScreen.route + "/$id")
                    },
                    onDelete = { viewmodel.Deletewish(wish) } // Notify parent component about deletion
                )
            }}
        }
    }
}


@Composable
fun WishItem(wish: Wish, onclick: () -> Unit, onDelete: () -> Unit) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 8.dp, start = 8.dp)
            .clickable { onclick() }

    ) {
        Row {
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.85f)) {
                Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
                Text(text = wish.description)

            }
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                IconButton(onClick = {onDelete()},){
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }

        }

    }
}
