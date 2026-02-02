package com.muhammetgumus.nutrition_book.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Nutritional::class], version = 1)
abstract  class NutritionalDatabase : RoomDatabase () {
    abstract  fun nutritionalDao() : NutritionalDao



    companion object {

     @Volatile
     private var instance : NutritionalDatabase? = null

     private val lock = Any()



        operator fun invoke(context: Context) : NutritionalDatabase = instance ?:synchronized(lock){

            instance ?: createDatabase(context).also {
                instance = it
            }

        }

        private fun createDatabase(context: Context): NutritionalDatabase = Room.databaseBuilder(
           context.applicationContext, NutritionalDatabase::class.java, "nutritionaldatabase"
        ).build()



    }





}