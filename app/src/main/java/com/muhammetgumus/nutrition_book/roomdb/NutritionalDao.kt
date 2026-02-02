package com.muhammetgumus.nutrition_book.roomdb


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NutritionalDao {

   @Query("SELECT * FROM Nutritional")
   suspend fun getAll(): List<Nutritional>

   @Query("SELECT * FROM Nutritional WHERE uuid = :uuid")
   suspend fun get(uuid: Int): Nutritional

   @Query("Delete from Nutritional")
   suspend fun deleteAll()

   @Insert
   suspend fun insertAll(vararg nutritionals: Nutritional): List<Long>





}