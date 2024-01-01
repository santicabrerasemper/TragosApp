package com.example.tragosapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.domain.DrinkDAO

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun drinkDAO(): DrinkDAO

    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "drink_table"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}