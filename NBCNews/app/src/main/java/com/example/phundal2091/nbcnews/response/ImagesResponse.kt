package com.example.phundal2091.nbcnews.response

import com.google.gson.annotations.SerializedName

/**
 * Created by phundal2091 on 5/9/18.
 */
data class ImagesResponse(@SerializedName("id")  val _id : Long?,
                          @SerializedName("url")  val _url : String?,
                          @SerializedName("headline")  val _headline : String?,
                          @SerializedName("published")  val _published : String?,
                          @SerializedName("caption")  val _caption : String?,
                          @SerializedName("copyright")  val _copyright : String?,
                          @SerializedName("graphic")  val _graphic : String?) {
}