package com.arysugiarto.movies.data.repo.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.arysugiarto.movies.BuildConfig
import com.arysugiarto.movies.data.model.ModelMovie
import com.arysugiarto.movies.data.response.ModelMovieResponse
import com.arysugiarto.movies.data.response.ModelTvResponse
import com.arysugiarto.movies.data.model.ModelTvShow
import com.arysugiarto.movies.helper.ApiInterface
import com.arysugiarto.movies.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val apiInterface: ApiInterface) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiInterface: ApiInterface): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiInterface)
            }
    }

    fun getDataMovie(): LiveData<ApiResponse<List<ModelMovie>?>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<ModelMovie>?>>()
        val call = apiInterface.getTopMovies(BuildConfig.API)
        call.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ModelMovieResponse> {
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable?) {
                }
                override fun onNext(value: ModelMovieResponse?) {
                    resultMovie.value = ApiResponse.success(value?.results)
                    EspressoIdlingResource.decrement()
                }
                override fun onError(e: Throwable?) {
                }

            })
        return resultMovie
    }

    fun getDataTvShow(): LiveData<ApiResponse<List<ModelTvShow>?>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<ModelTvShow>?>>()
        val call = apiInterface.getTopTvShows(BuildConfig.API)
        call.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ModelTvResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {

                }

                override fun onNext(value: ModelTvResponse?) {
                    resultTvShow.value = ApiResponse.success(value?.results)
                    EspressoIdlingResource.decrement()
                }

                override fun onError(e: Throwable?) {
                }

            })
        return resultTvShow
    }


}