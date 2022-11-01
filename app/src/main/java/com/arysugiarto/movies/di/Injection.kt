package com.arysugiarto.movies.di

import android.content.Context
import com.arysugiarto.movies.data.repo.Repo
import com.arysugiarto.movies.data.repo.local.LocalRepo
import com.arysugiarto.movies.data.repo.local.room.DbMovie
import com.arysugiarto.movies.data.repo.remote.RemoteDataSource
import com.arysugiarto.movies.helper.RestAPI
import com.arysugiarto.movies.helper.ApiInterface
import com.arysugiarto.movies.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): Repo {

        val database = DbMovie.getInstance(context)


        val remoteDataSource = RemoteDataSource.getInstance(
            RestAPI().getClient().create(ApiInterface::class.java)
        )
        val localDataSource = LocalRepo.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return Repo.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}