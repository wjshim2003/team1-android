package com.waffle22.wafflytime.ui.boards.boardscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.waffle22.wafflytime.databinding.BoardThreadBinding
import com.waffle22.wafflytime.network.dto.PostResponse
import com.waffle22.wafflytime.network.dto.TimeDTO
import java.time.LocalDate

class PostPreviewAdapter(private val clicked: () -> Unit)
    : ListAdapter<PostResponse, PostPreviewAdapter.PostAbstractViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAbstractViewHolder {
        return PostAbstractViewHolder(
            BoardThreadBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), parent.context
        )
    }

    override fun onBindViewHolder(holder: PostAbstractViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clicked)
    }

    class PostAbstractViewHolder(private var binding: BoardThreadBinding, private var context: Context)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(postAbstract: PostResponse, clicked: () -> Unit) {
            binding.apply{
                nickname.text = postAbstract.nickname
                time.text = timeToText(postAbstract.createdAt)
                previewText.text = postAbstract.contents
                likesText.text = postAbstract.nlikes.toString()
                commentsText.text = postAbstract.nreplies.toString()
                layout.setOnClickListener{clicked()}
            }
        }
        private fun timeToText(time: TimeDTO): String{
            var timeText = time.month.toString() + '/' + time.day.toString() + ' ' + time.hour.toString() + ':' + time.minute.toString()
            if (LocalDate.now().year != time.year)
                timeText = time.year.toString() + '/' + timeText
            return timeText
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<PostResponse>() {
            override fun areContentsTheSame(
                oldItem: PostResponse,
                newItem: PostResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
                return oldItem.postId == newItem.postId
            }
        }
    }
}