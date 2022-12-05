package com.turgutkurt.boynermobilandroid.model

import com.google.gson.annotations.SerializedName

data class NewsModel(@SerializedName("name")
                     //isimler aynıysa serializedName yazmama gerek yok
                     var name: String,
                     @SerializedName("description")
                     var description: String,
                     @SerializedName("url")
                     var url: String ,
                     @SerializedName("country")
                     var country:String,
                     @SerializedName("category")
                     var category:String,
)