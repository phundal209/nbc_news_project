package com.example.phundal2091.nbcnews.response

import com.google.gson.annotations.SerializedName

/**
 * Created by phundal2091 on 5/9/18.
 */
data class ItemsResponse(@SerializedName("id")  val _id : String?,
                         @SerializedName("type")  val _type : String?,
                         @SerializedName("url")  val _url : String?,
                         @SerializedName("headline")  val _headline : String?,
                         @SerializedName("duration") val _duration : String?,
                         @SerializedName("published")  val _published : String?,
                         @SerializedName("tease")  val _tease : String?,
                         @SerializedName("summary")  val _summary : String?,
                         @SerializedName("label")  val _label : String?,
                         @SerializedName("videoUrl")  val _videoUrl : String?,
                         @SerializedName("breaking")  val _breaking : Boolean?,
                         @SerializedName("preview")  val _preview : String?,
                         @SerializedName("images") val  _images : List<ImagesResponse>?)