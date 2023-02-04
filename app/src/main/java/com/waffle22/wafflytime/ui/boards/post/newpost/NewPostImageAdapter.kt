package com.waffle22.wafflytime.ui.boards.post.newpost

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.waffle22.wafflytime.databinding.NewPostImageBinding
import com.waffle22.wafflytime.network.dto.ImageRequest
import com.bumptech.glide.Glide

class NewPostImageAdapter(
    private val onEditDescription: (ImageRequest, String) -> Unit,
    private val onDeleteImage: (ImageRequest) -> Unit
)
    : ListAdapter<ImageStorage, NewPostImageAdapter.NewPostImageViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPostImageViewHolder {
        return NewPostImageViewHolder(
            NewPostImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), parent.context
        )
    }

    override fun onBindViewHolder(holder: NewPostImageViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, onEditDescription, onDeleteImage)
    }

    class NewPostImageViewHolder(private var binding: NewPostImageBinding, private val context: Context)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(
            image: ImageStorage,
            onEditDescription: (ImageRequest, String) -> Unit,
            onDeleteImage: (ImageRequest) -> Unit
        ){
            Glide.with(context)
                .asBitmap()
                .load(image.byteArray)
                .into(binding.image)
            binding.addDescription.setOnClickListener {
                val editText = EditText(context)
                editText.setText(image.imageRequest.description)
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("이미지 설명을 입력하세요")
                dialog.setView(editText)
                dialog.setPositiveButton("확인"
                ) { _, _ ->
                    onEditDescription(image.imageRequest, editText.text.toString())
                }
                dialog.setNegativeButton("취소"
                ) { _, _ -> null }
                dialog.show()
            }
            binding.deleteImage.setOnClickListener {
                onDeleteImage(image.imageRequest)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ImageStorage>() {
            override fun areContentsTheSame(
                oldItem: ImageStorage,
                newItem: ImageStorage
            ): Boolean {
                return oldItem.imageRequest == newItem.imageRequest
            }

            override fun areItemsTheSame(
                oldItem: ImageStorage,
                newItem: ImageStorage
            ): Boolean {
                return oldItem.imageRequest.imageId == newItem.imageRequest.imageId
            }
        }
    }
}