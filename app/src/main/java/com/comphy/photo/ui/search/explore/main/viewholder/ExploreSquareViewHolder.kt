package com.comphy.photo.ui.search.explore.main.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comphy.photo.databinding.ItemExploreSquareBinding

class ExploreSquareViewHolder(
    var binding: ItemExploreSquareBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        view: View,
        imageUrl: String
    ) {
        Glide.with(view)
            .load(imageUrl)
            .centerCrop()
            .into(binding.imgExplore)
    }

}