package com.comphy.photo.ui.community.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.comphy.photo.data.source.remote.response.post.feed.FeedResponseContentItem
import com.comphy.photo.databinding.ItemPostImageBinding
import com.comphy.photo.databinding.ItemPostTextBinding
import com.comphy.photo.databinding.ItemPostVideoBinding
import com.comphy.photo.ui.main.fragment.feed.viewholder.FeedImageViewHolder
import com.comphy.photo.ui.main.fragment.feed.viewholder.FeedTextViewHolder
import com.comphy.photo.ui.main.fragment.feed.viewholder.FeedVideoViewHolder
import com.comphy.photo.vo.FeedType
import com.google.android.exoplayer2.ExoPlayer

class CommunityDetailPostAdapter(
    private val player: ExoPlayer?,
    private val onLikePressed: (position: Int, postId: String, isLiked: Boolean) -> Unit
) : PagingDataAdapter<FeedResponseContentItem, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FeedType.TEXT -> FeedTextViewHolder(
                ItemPostTextBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            FeedType.IMAGE -> FeedImageViewHolder(
                ItemPostImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            FeedType.VIDEO -> FeedVideoViewHolder(
                ItemPostVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Missing view type exception ViewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feed = getItem(position)!!

        when (holder.itemViewType) {
//            FeedType.TEXT -> (holder as FeedTextViewHolder).bind(holder.itemView, feed) { postId, isLiked ->
//                onLikePressed(position, postId, isLiked)
//            }
//            FeedType.IMAGE -> (holder as FeedImageViewHolder).bind(holder.itemView, feed) { postId, isLiked ->
//                onLikePressed(position, postId, isLiked)
//            }
//            FeedType.VIDEO -> (holder as FeedVideoViewHolder).bind(holder.itemView, feed) { postId, isLiked ->
//                onLikePressed(position, postId, isLiked)
//            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val feed = getItem(position)

        return when {
            feed?.linkPhoto != null -> FeedType.IMAGE
            feed?.linkVideo != null -> FeedType.VIDEO
            else -> FeedType.TEXT
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<FeedResponseContentItem>() {
            override fun areItemsTheSame(oldItem: FeedResponseContentItem, newItem: FeedResponseContentItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FeedResponseContentItem, newItem: FeedResponseContentItem): Boolean =
                oldItem == newItem
        }
    }
}