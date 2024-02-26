package com.example.myrecipeapp

data class Meal(val strMeal:String,
                   val strMealThumb:String,
                   val idMeal:String)


data class mealresponse(val meals:List<Meal>)