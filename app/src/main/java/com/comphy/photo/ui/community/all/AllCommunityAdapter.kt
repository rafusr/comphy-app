package com.comphy.photo.ui.community.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comphy.photo.R
import com.comphy.photo.data.source.remote.response.community.follow.FollowCommunityResponseContentItem
import com.comphy.photo.data.source.remote.response.community.join.JoinedCommunityResponseContentItem
import com.comphy.photo.databinding.ItemCommunityBinding

class AllCommunityAdapter(
    private val createdCommunities: List<FollowCommunityResponseContentItem>?,
    private val joinedCommunities: List<JoinedCommunityResponseContentItem>?,
    private val onOptionClick: (Int) -> Unit
) : RecyclerView.Adapter<AllCommunityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllCommunityAdapter.ViewHolder {
        val view = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllCommunityAdapter.ViewHolder, position: Int) {
        when {
            createdCommunities != null -> {
                val community = createdCommunities[position]

                Glide.with(holder.itemView)
                    .load(community.profilePhotoCommunityLink)
                    .error(R.drawable.ic_people_community)
                    .placeholder(R.drawable.ic_people_community)
                    .centerCrop()
                    .into(holder.binding.imgCommunity)
                holder.binding.txtCommunityTitle.text = community.communityName
                holder.binding.txtCommunityCategory.text = community.categoryCommunity.name
                holder.binding.btnOption.setOnClickListener { onOptionClick(community.id) }
            }
            joinedCommunities != null -> {
                val community = joinedCommunities[position].community

                Glide.with(holder.itemView)
                    .load(community.profilePhotoCommunityLink)
                    .error(R.drawable.ic_people_community)
                    .placeholder(R.drawable.ic_people_community)
                    .centerCrop()
                    .into(holder.binding.imgCommunity)
                holder.binding.txtCommunityTitle.text = community.communityName
                holder.binding.txtCommunityCategory.text = community.categoryCommunity.name
                holder.binding.btnOption.setOnClickListener { onOptionClick(community.id) }
            }
        }
    }

    override fun getItemCount(): Int = createdCommunities?.size ?: joinedCommunities?.size ?: 2

    inner class ViewHolder(var binding: ItemCommunityBinding) :
        RecyclerView.ViewHolder(binding.root)
}