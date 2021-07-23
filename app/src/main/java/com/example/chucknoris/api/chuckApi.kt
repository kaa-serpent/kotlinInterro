package com.example.chucknoris.api


interface Chuckfacts {
/*
    @GET("/jokes")
    fun getfacts():Call<AllFacts>
*/
}
data class AllFacts(
    val results: List<Result>
)

data class Result(
    val id: Int,    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)