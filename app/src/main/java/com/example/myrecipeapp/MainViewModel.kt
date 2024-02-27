package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel(){


    private var _submealState = mutableStateOf(Submealstate())
    val submealstate:State<Submealstate> = _submealState


    private var _categoriesState = mutableStateOf(Recipestate())
    val categoriesState:State<Recipestate> = _categoriesState

    init {
        fetchcategories()
    }
    init {
        fetchsubmeal()
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

    //need to solve this category as parameter right now just passing it with "Vegetarian"
    fun fetchsubmeal(){
        viewModelScope.launch {
            try {
                val responsesubmeal= submealservice.getsubMeals("Vegetarian")
                _submealState.value=_submealState.value.copy(
                    list1 = responsesubmeal.meals,
                    loading1 = false,
                    error1 = null)
            }catch(e:Exception){
               _submealState.value=_submealState.value.copy(
                   loading1 = false,
                   error1 = "error fetching data${e.message}")
            }
        }
    }




    data class Recipestate(
        val loading:Boolean=true,
        val list:List<Category> = emptyList(),
        val error:String?=null
    )

    data class Submealstate(
        val loading1:Boolean=true,
        val list1:List<Meal> = emptyList(),
        val error1:String?=null,

    )
}