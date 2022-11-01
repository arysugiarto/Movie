package com.arysugiarto.movies.helper

import io.reactivex.Observable
import com.arysugiarto.movies.data.response.ModelMovieResponse
import com.arysugiarto.movies.data.response.ModelTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/top_rated") fun getTopMovies(@Query("api_key") apiKey: String): Observable<ModelMovieResponse>

    @GET("tv/top_rated") fun getTopTvShows(@Query("api_key") apiKey: String): Observable<ModelTvResponse>

}