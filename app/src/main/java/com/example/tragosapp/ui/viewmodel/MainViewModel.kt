package com.example.tragosapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.domain.Repo
import com.example.tragosapp.vo.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {

    private val drinkData = MutableLiveData<String>()

    fun setDrink(drinkName: String) {
        drinkData.value = drinkName
    }

    init {
        setDrink("margarita")
    }

    val fetchTragosList = drinkData.distinctUntilChanged().switchMap { drinkName ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getDrinkList(drinkName))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun SaveDrink(drink: DrinkEntity) {
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }

    fun getFavouriteDrink() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getFavouriteDrink())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

  /*  fun deleteDrink(drink: DrinkEntity) {
        viewModelScope.launch{
            repo.deleteDrink(drink)
        }
    }*/
}