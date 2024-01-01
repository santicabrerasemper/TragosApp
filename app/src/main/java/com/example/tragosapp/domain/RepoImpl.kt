package com.example.tragosapp.domain

import com.example.tragosapp.data.datasource.DataSourceImpl
import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.vo.Resource

class RepoImpl(private val dataSource: DataSourceImpl): Repo {
     override suspend fun getDrinkList(drinkName: String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getFavouriteDrink(): Resource<List<DrinkEntity>> {
        return dataSource.getDrinkFavourites()
    }

    override suspend fun insertDrink(trago: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(trago)
    }
}