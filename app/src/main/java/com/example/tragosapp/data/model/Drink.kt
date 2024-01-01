package com.example.tragosapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val drinkId: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strDrinkAlcohol")
    val hasAlcohol: String = ""
): Parcelable

data class DrinkList(
    @SerializedName("drinks")
    val drinkList: List<Drink>)

@Entity(tableName = "drinksEntity")
data class DrinkEntity(
    @PrimaryKey
    val drinkId: String,
    @ColumnInfo(name ="drink_img")
    val image: String = "",
    @ColumnInfo(name ="drink_name")
    val name: String = "",
    @ColumnInfo(name ="drink_instructions")
    val description: String = "",
    @ColumnInfo(name ="drink_alcohol")
    val hasAlcohol: String = ""
)