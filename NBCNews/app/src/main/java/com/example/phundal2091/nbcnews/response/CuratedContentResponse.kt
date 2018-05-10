package com.example.phundal2091.nbcnews.response

import com.google.gson.annotations.SerializedName

/**
 * Created by phundal2091 on 5/9/18.
 */
data class CuratedContentResponse(@SerializedName("id")  val _id : String?,
                                  @SerializedName("type")  val _type : String?,
                                  @SerializedName("tease")  val _tease : String?,
                                  @SerializedName("header") val _header : String?,
                                  @SerializedName("subHeader") val _subHeader : String?,
                                  @SerializedName("items") val _items : List<ItemsResponse>?) {
}