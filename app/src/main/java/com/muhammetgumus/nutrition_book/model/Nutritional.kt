package com.muhammetgumus.nutrition_book.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Nutritional(

    @ColumnInfo(name = "name")
    @SerializedName("isim")
    val name: String?,

    @ColumnInfo(name = "calories")
    @SerializedName("kalori")
    val calories: String?,

    @ColumnInfo(name = "carbohydrate")
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,

    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val protein: String?,

    @ColumnInfo(name = "fat")
    @SerializedName("yag")
    val fat: String?,

    @ColumnInfo(name = "image_url")
    @SerializedName("gorsel")
    val imageUrl: String?

) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

