package com.example.tragosapp.data.datasource

import com.example.tragosapp.AppDataBase
import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.domain.DataSource
import com.example.tragosapp.vo.Resource
import com.example.tragosapp.vo.RetrofitClient

class DataSourceImpl(private val appDataBase: AppDataBase) :
    DataSource {

    override suspend fun getDrinkByName(drinkName: String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getTragoByName(drinkName).drinkList)
    }

    override suspend fun insertDrinkIntoRoom(drink: DrinkEntity) {
        appDataBase.drinkDAO().insertFavourite(drink)
    }

    override suspend fun getDrinkFavourites(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDataBase.drinkDAO().getAllFavouriteDrinks())

    }
}