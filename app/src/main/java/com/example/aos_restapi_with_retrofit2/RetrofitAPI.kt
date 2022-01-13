package com.example.aos_restapi_with_retrofit2

import com.example.aos_restapi_with_retrofit2.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("searchWeeklyBoxOfficeList.json?")
    fun getMovie(
        @Query("key") key: String,
        @Query("weekGb") weekGb: Int,
        @Query("targetDt") targetDt: Int,
        @Query("itemPerPage") itemPerPage: Int
    ) : Call<Movie>
}