package com.comphy.photo.ui.main.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comphy.photo.R
import com.comphy.photo.data.source.remote.response.event.EventResponseContentItem
import com.comphy.photo.databinding.ItemSlidePagerBinding
import splitties.resources.drawable

class HomePagerAdapter(
    private val events: List<EventResponseContentItem>
) : RecyclerView.Adapter<HomePagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePagerAdapter.ViewHolder {
        val view = ItemSlidePagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomePagerAdapter.ViewHolder, position: Int) {
        val event = events[position]

        with(holder.binding) {
            Glide.with(holder.itemView)
                .load(event.linkBanner)
                .placeholder(holder.itemView.drawable(R.drawable.img_banner_placeholder))
                .error(holder.itemView.drawable(R.drawable.img_banner_placeholder))
                .centerCrop()
                .into(imgSlide)
        }
    }

    override fun getItemCount(): Int = if(events.size > 3) 3 else events.size

    inner class ViewHolder(var binding: ItemSlidePagerBinding) :
        RecyclerView.ViewHolder(binding.root)
}