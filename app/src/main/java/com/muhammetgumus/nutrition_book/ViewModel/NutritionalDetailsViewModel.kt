package com.muhammetgumus.nutrition_book.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammetgumus.nutrition_book.roomdb.Nutritional
import com.muhammetgumus.nutrition_book.roomdb.NutritionalDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NutritionalDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private var nutritionalDatabase = NutritionalDatabase(getApplication())
    private val _nutritionalLiveData = MutableLiveData<Nutritional>()
            val nutritionalLiveData: LiveData<Nutritional> = _nutritionalLiveData





    fun getDataFromRoom(uuid : Int) {

        viewModelScope.launch {
          val nutritionalList = nutritionalDatabase.nutritionalDao().get(uuid)
            _nutritionalLiveData.value = nutritionalList
        }




     }



}