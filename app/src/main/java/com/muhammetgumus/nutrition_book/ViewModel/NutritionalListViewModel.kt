package com.muhammetgumus.nutrition_book.ViewModel


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.besinkitabi.util.SpecialSharedPreferences
import com.muhammetgumus.nutrition_book.roomdb.Nutritional
import com.muhammetgumus.nutrition_book.roomdb.NutritionalDatabase
import com.muhammetgumus.nutrition_book.service.NutritionalAPIServis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.toTypedArray

class NutritionalListViewModel(application: Application): AndroidViewModel(application) {

    private  val nutritionalLiveData = MutableLiveData<List<Nutritional>>()
             val _nutritionalLiveData: LiveData<List<Nutritional>> = nutritionalLiveData
    private  val errorMessage = MutableLiveData<Boolean>()
             val _errorMessage: LiveData<Boolean> = errorMessage
    private  val loading = MutableLiveData<Boolean>()
            val _loading: LiveData<Boolean> = loading






      private var NutritionalDatabase = NutritionalDatabase(getApplication())


      private val specialSharedPreferences = SpecialSharedPreferences(getApplication())
      private val NutritionalAPIServis = NutritionalAPIServis()


      private val uploadtime = 0.2*60*1000*1000*1000L


    fun refreshData(){

        val savetime =specialSharedPreferences.getTime()

        if (savetime!=null && savetime != 0L && System.nanoTime() - savetime < uploadtime) {
            // get from Sqlite
            getDataFromSqlite()
        } else {
            //get from Internet
            getTheDataFromTheInternet()
        }
    }





     private fun getDataFromSqlite(){
        loading.value=true

        viewModelScope.launch(Dispatchers.IO){
            val nutritionalList  = NutritionalDatabase.nutritionalDao().getAll()

       viewModelScope.launch(Dispatchers.Main) {

           showNutritional(nutritionalList)
           Toast.makeText(getApplication(), "data from sqlite", Toast.LENGTH_LONG).show()

       }

        }
    }






      fun getTheDataFromTheInternet(){
               loading.value=true

            viewModelScope.launch(Dispatchers.IO) {
                  val apiList = NutritionalAPIServis.getData()

                     withContext(Dispatchers.Main) {
                        loading.value = false
                         saveRoom(apiList)
                        Toast.makeText(getApplication(), "data from internet", Toast.LENGTH_LONG).show()


                  }


            }      }





      private fun saveRoom(nutritionallist : List<Nutritional>) {

         viewModelScope.launch {

             val dao =  NutritionalDatabase.nutritionalDao()
             dao.deleteAll()

             val uuidList = dao.insertAll(*nutritionallist.toTypedArray())
             var i = 0
             while (i < uuidList.size) {
                   nutritionallist[i].uuid = uuidList[i].toInt()
                 i=i+1
             }

            showNutritional(nutritionallist)
         }
          specialSharedPreferences.saveTime(System.nanoTime())

      }




      private fun showNutritional (nutritionallist : List<Nutritional>) {
        nutritionalLiveData.value = nutritionallist
        errorMessage.value = false
        loading.value = false

         }




}







