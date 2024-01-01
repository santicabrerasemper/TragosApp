package com.example.tragosapp.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tragosapp.data.model.DrinkEntity

@Dao
interface DrinkDAO {

   @Query("SELECT * FROM drinksEntity")
   suspend fun getAllFavouriteDrinks(): List<DrinkEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertFavourite(drink: DrinkEntity)
}