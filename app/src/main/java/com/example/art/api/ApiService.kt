package com.example.art.api

import com.example.art.model.ListOfEntities
import retrofit2.Call
import com.example.art.model.User
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/footscray/auth")
    fun getKeypass(@Body user: User) : Call<JsonObject>
    @GET("/dashboard/{keypass}")
    fun getData(@Path("keypass") keypass: String): Call<ListOfEntities>
}