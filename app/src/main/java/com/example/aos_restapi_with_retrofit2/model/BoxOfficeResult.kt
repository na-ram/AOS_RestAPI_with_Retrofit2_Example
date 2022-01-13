package com.example.aos_restapi_with_retrofit2.model

data class BoxOfficeResult(
    val boxofficeType: String,
    val showRange: String,
    val weeklyBoxOfficeList: List<WeeklyBoxOffice>,
    val yearWeekTime: String
)