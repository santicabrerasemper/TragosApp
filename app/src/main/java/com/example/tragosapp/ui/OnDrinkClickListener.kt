package com.example.tragosapp.ui

import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity

interface OnDrinkClickListener {
    fun onDrinkClick(drink: Drink,position: Int)
}