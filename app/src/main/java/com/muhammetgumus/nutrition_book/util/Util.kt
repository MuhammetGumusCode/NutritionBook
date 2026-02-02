package com.muhammetgumus.nutrition_book.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/*
fun String.myextensions (parameter : String) {

      println(parameter)
}*/




 fun ImageView.dowloandurl (url : String?, placeholder : CircularProgressDrawable) {

       val options = RequestOptions().placeholder(placeholder)

      Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)


  }




      fun placeholderr (context : Context) : CircularProgressDrawable {
          return  CircularProgressDrawable(context).apply {
               strokeWidth=8f
               centerRadius=40f
               start()

          }



}