package com.arysugiarto.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arysugiarto.movies.data.repo.Repo
import com.arysugiarto.movies.data.repo.local.entity.MovieEntity
import com.arysugiarto.movies.vo.Resource

class MovieViewModel(private val repo: Repo) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = repo.getAllMovies()

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = repo.getFavoriteMovies()
}