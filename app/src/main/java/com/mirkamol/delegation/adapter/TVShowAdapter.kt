package com.mirkamol.delegation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mirkamol.delegation.R
import com.mirkamol.delegation.databinding.ItemTvShowBinding
import com.mirkamol.delegation.fragments.TvShowFragment
import com.mirkamol.delegation.model.TVShow

class TVShowAdapter(var tvShowFragment: TvShowFragment, var items: ArrayList<TVShow>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun setViewTVShows(tvShows: ArrayList<TVShow>) {
        // items.clear()
        items.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TVShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tvShow: TVShow = items[position]
        if (holder is TVShowViewHolder) {
            Glide.with(tvShowFragment).load(tvShow.image_thumbnail_path)
                .into(holder.binding.ivMovie)

            holder.binding.tvName.text = tvShow.name
            holder.binding.tvType.text = tvShow.network

            ViewCompat.setTransitionName(holder.binding.ivMovie, tvShow.name)
            

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TVShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTvShowBinding.bind(view)
    }
}