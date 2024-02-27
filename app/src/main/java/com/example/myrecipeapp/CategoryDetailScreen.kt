package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter



@Composable
fun CategoryDetailScreen(category: Category) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = category.strCategory, textAlign = TextAlign.Center)

        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = category.strCategory,
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(2f)
        )
        Divider(color = Color.Red, thickness = 3.dp, modifier = Modifier.shadow(5.dp, spotColor = Color.Black))
        Box(modifier = Modifier.height(200.dp)){
        Text(
            text = category.strCategoryDescription,
            textAlign = TextAlign.Justify,
            modifier = Modifier.verticalScroll(rememberScrollState())
        )}

        val recipeViewmodel:MainViewModel= viewModel()
        val viewstate by recipeViewmodel.submealstate




        submealscreen(viewstate = viewstate,)



        
        
    }
}

@Composable
fun submealscreen(modifier: Modifier=Modifier,viewstate: MainViewModel.Submealstate){
    Box (modifier = Modifier.fillMaxSize().clickable {}){
        when{
            viewstate.loading1->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewstate.error1 != null->{
                Text(text = "Error Occured")
            }
            else->{
                submealdetail(meals = viewstate.list1)
            }

        }
    }

}








@Composable
fun submealdetail(meals: List<Meal>){
    LazyVerticalGrid(GridCells.Fixed(3), modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)){
        items(meals){
            meal->
            eachmeal(meal = meal)
        }
    }
}

@Composable
fun eachmeal(meal: Meal){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = rememberAsyncImagePainter(model = meal.strMealThumb), contentDescription =null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.5f))
        Text(text = meal.strMeal,
            color = Color.LightGray,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp))


    }
}
