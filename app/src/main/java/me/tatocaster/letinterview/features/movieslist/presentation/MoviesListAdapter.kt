package me.tatocaster.letinterview.features.movieslist.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_tv_show.view.*
import me.tatocaster.letinterview.R
import me.tatocaster.letinterview.entity.TvShow
import me.tatocaster.letinterview.utils.GlideApp
import java.util.*

class MoviesListAdapter(private val context: Context, private val listener: (Int) -> Unit) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {
    private val tvShowsData = arrayListOf<TvShow>()

    fun updateData(data: ArrayList<TvShow>) {
        tvShowsData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tvShowsData[position]
        holder.bindView(position, item)
    }

    override fun getItemCount(): Int {
        return tvShowsData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int, item: TvShow) {
            itemView.setOnClickListener({ v ->
                listener(position)
            })

            itemView.textViewTvShowTitle.text = item.name
            itemView.textViewTvShowDate.text = item.firstAirDate

            GlideApp.with(context)
                    .load("https://image.tmdb.org/t/p/w500/${item.posterPath}")
//                    .load("https://image.tmdb.org/t/p/w780/${item.backdropPath}")
                    .into(itemView.imageViewTvShowPoster)
        }

    }
}