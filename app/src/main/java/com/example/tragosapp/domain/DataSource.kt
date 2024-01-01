package com.example.tragosapp.domain

import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.vo.Resource

interface DataSource {
    suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>>
    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)
    suspend fun getDrinkFavourites(): Resource<List<DrinkEntity>>
}