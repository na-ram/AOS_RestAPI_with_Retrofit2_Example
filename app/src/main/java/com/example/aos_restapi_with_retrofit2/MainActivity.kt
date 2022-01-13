package com.example.aos_restapi_with_retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.aos_restapi_with_retrofit2.model.Movie
import com.example.aos_restapi_with_retrofit2.util.Const.Companion.RestAPIKey
import com.example.aos_restapi_with_retrofit2.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private val tvRange by lazy {
        findViewById<TextView>(R.id.tvRange)
    }

    private val tvMovieNm by lazy {
        findViewById<TextView>(R.id.tvMovieNm)
    }

    private val tvMovieRank by lazy {
        findViewById<TextView>(R.id.tvMovieRank)
    }

    private val tvTodayAudience by lazy {
        findViewById<TextView>(R.id.tvTodayAudience)
    }

    private val tvAccumulatedAudience by lazy {
        findViewById<TextView>(R.id.tvAccumulatedAudience)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestToRestAPI()
    }

    private fun requestToRestAPI() {
        val retrofit = RetrofitClient.getInstance()

        val service = retrofit.create(RetrofitAPI::class.java)

        service.getMovie(RestAPIKey, 0, 20220101, 1)
            .enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    Log.d("Success", response.body().toString())

                    if (response.isSuccessful) {
                        response.body()?.let {
                            processMovie(response.body()!!)
                        }
                    }

                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.d("Failure", t.localizedMessage)
                }

            })
    }

    private fun processMovie(movie: Movie) {

        val range = movie.boxOfficeResult.showRange

        var from = range.split("~")[0]
        var to = range.split("~")[1]

        val stringToDateFormat = SimpleDateFormat("yyyyMMdd")

        val fromDate = stringToDateFormat.parse(from)
        val toDate = stringToDateFormat.parse(to)

        val dateToStringFormat = SimpleDateFormat("yyyy/MM/dd")

        from = dateToStringFormat.format(fromDate)
        to = dateToStringFormat.format(toDate)

        tvRange.text = "[$from ~ $to]"

        movie.boxOfficeResult.weeklyBoxOfficeList.forEach {
            tvMovieNm.text = it.movieNm
            tvMovieRank.text = it.rank
            tvTodayAudience.text = it.audiCnt
            tvAccumulatedAudience.text = it.audiAcc
        }
    }

}