package com.example.aos_restapi_with_retrofit2.util

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance!!
    }
}