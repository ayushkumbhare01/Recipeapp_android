package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel(){

    private var _categoriesState = mutableStateOf(Recipestate())
    val categoriesState:State<Recipestate> = _categoriesState

    init {
        fetchcategories()
    }

    private fun fetchcategories(){
        viewModelScope.launch {
            try {
                val response = recipeservice.getCategories()
                _categoriesState.value=_categoriesState.value.copy(list = response.categories,
                    loading = false,
                    error=null)

            }catch (e:Exception){
                _categoriesState.value=_categoriesState.value.copy(
                    loading = false,
                    error = "error fetching data${e.message}")
            }
        }
    }



    data class Recipestate(
        val loading:Boolean=true,
        val list:List<Category> = emptyList(),
        val error:String?=null
    )
}