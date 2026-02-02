package com.muhammetgumus.nutrition_book.service


import com.muhammetgumus.nutrition_book.roomdb.Nutritional
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NutritionalAPIServis {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NutritionalAPI::class.java)


       suspend fun getData () : List<Nutritional> {
           return retrofit.getNutritional()

         }






}