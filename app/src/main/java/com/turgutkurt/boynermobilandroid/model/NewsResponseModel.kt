package com.turgutkurt.boynermobilandroid.model

import com.google.gson.annotations.SerializedName

data class  NewsResponseModel (
    @SerializedName("status")
    var status:String="",
    @SerializedName("sources")
    var sources: List<NewsModel>
){
    @JvmName("getStatus1")
    fun getStatus():String {
        return status
    }
    @JvmName("setStatus1")
    fun setStatus(status:String) {
        this.status = status;
    }
    @JvmName("getSources1")
    fun getSources (): List<NewsModel> {
        return sources
    }
}
