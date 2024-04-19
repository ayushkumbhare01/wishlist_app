package com.example.wishlist_app

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlist_app.Data.Wish


@Composable
fun AddEditDetailView(id:Long,
                      viewmodel: WishViewmodel,
                      navController: NavController){

//    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    if(id != 0L){
       val wish = viewmodel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewmodel.wishTitleState=wish.value.title
        viewmodel.wishdescriptionState=wish.value.description
    }else{
        viewmodel.wishTitleState=""
        viewmodel.wishdescriptionState=""
    }




    Scaffold(scaffoldState = scaffoldState,
        topBar = { AppBarView(Title =
    if(id != 0L) stringResource(id = R.string.Update_wish)
    else stringResource(id = R.string.add_wish)){navController.navigateUp()}
     },)
    {

        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize()
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally,
            ){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Title",
                value = viewmodel.wishTitleState,
                onValueChanged = {viewmodel.onWishTitlechanged(it)})
            WishTextField(label = "Description",
                value = viewmodel.wishdescriptionState,
                onValueChanged = {viewmodel.onwishDescriptionChanged(it)})
            Spacer(modifier = Modifier.height(10.dp))
            
            Button(onClick = {
                if (viewmodel.wishTitleState.isNotEmpty() &&
                    viewmodel.wishdescriptionState.isNotEmpty()){
                    if(id!=0L){
                       viewmodel.UpdateWish(Wish(id = id,
                           title = viewmodel.wishTitleState.trim(),
                           description = viewmodel.wishdescriptionState.trim()))
                        Toast.makeText(context,"Wish Updated Successfully",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        //addwish
                       viewmodel.Addwish(
                           Wish(title = viewmodel.wishTitleState.trim()
                           , description =viewmodel.wishdescriptionState.trim())
                       )
                        Toast.makeText(context,"Wish Added Successfully",Toast.LENGTH_SHORT).show()
                    }

                    navController.navigateUp()
                }else{
                    Toast.makeText(context,"Enter all the required fields",Toast.LENGTH_SHORT).show()
                }



            }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF539DB3))) {
                Text(text = if(id != 0L) stringResource(id = R.string.Update_wish)
                else stringResource(id = R.string.add_wish), color = Color.White)
                
            }
            


        }


    }

}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    val color2 = remember { mutableStateOf(Color.Unspecified) }
    if(isSystemInDarkTheme()){color2.value= Color.LightGray}
    else{color2.value= Color.Black
    }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = if(isSystemInDarkTheme())
        {Color.White}else{
            Color.Black}) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), // Corrected parameter name
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = color2.value,
            focusedBorderColor = color2.value,
            unfocusedBorderColor = color2.value,
            cursorColor = color2.value,
            focusedLabelColor = color2.value,
            unfocusedLabelColor = color2.value
        )
    )
}

@Preview
@Composable
fun preview(){
    WishTextField(label = "label", value = "value", onValueChanged = {})
}