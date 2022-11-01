package com.arysugiarto.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arysugiarto.movies.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*
import com.arysugiarto.movies.R
import com.arysugiarto.movies.data.repo.local.entity.MovieEntity

class FavoriteMovieAdapter internal constructor(
    private val listener: (MovieEntity) -> Unit
) :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holderFavorite: FavoriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holderFavorite.bind(movie, listener)
        }
    }

    class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity, listener: (MovieEntity) -> Unit) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.IMG_URL + movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_image)
                            .error(R.drawable.ic_baseline_image)
                    )
                    .into(img_cover_item)

                setOnClickListener { listener(movie) }
            }
        }
    }
}