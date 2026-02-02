package com.muhammetgumus.nutrition_book.service


import com.muhammetgumus.nutrition_book.roomdb.Nutritional
import retrofit2.http.GET

 interface NutritionalAPI {

    // Base Url -> https://raw.githubusercontent.com/
    // Endpoint -> atilsamancioglu/BTK20-JSONVeriSeti/refs/heads/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getNutritional(): List<Nutritional>


}



