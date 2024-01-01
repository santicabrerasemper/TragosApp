package com.example.tragosapp.domain

import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.vo.Resource

interface Repo {
     suspend fun getDrinkList(drinkName: String): Resource<List<Drink>>
     suspend fun getFavouriteDrink(): Resource<List<DrinkEntity>>
     suspend fun insertDrink(drinkName: DrinkEntity)
}