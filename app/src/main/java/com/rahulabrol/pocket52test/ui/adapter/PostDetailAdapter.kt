package com.rahulabrol.pocket52test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rahulabrol.pocket52test.R
import com.rahulabrol.pocket52test.databinding.ItemPostDetailBinding
import com.rahulabrol.pocket52test.databinding.ItemPostEmptyBinding
import com.rahulabrol.pocket52test.model.OriginalPost

/**
 * Created by Rahul Abrol on 24/2/21.
 */
class PostDetailAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_CLASS = 1
    }

    private val items: MutableList<OriginalPost> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VIEW_TYPE_CLASS -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    DataBindingUtil.inflate<ItemPostDetailBinding>(
                        inflater,
                        R.layout.item_post_detail,
                        parent,
                        false
                    )
                return ItemPostDetailViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding =
                    DataBindingUtil.inflate<ItemPostEmptyBinding>(
                        inflater,
                        R.layout.item_post_empty,
                        parent,
                        false
                    )
                return EmptyViewHolder(binding)
            }
        }
    }

    fun addPostList(detailList: List<OriginalPost>?) {
        items.clear()
        if (detailList?.isNotEmpty() == true) {
            items.addAll(detailList)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemPostDetailViewHolder) {
            holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) {
            VIEW_TYPE_EMPTY
        } else {
            VIEW_TYPE_CLASS
        }
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty()) {
            1
        } else {
            items.size
        }
    }

    inner class ItemPostDetailViewHolder(private val binding: ItemPostDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: OriginalPost) {
            binding.apply {
                this.post = post
                executePendingBindings()
            }
        }
    }

    inner class EmptyViewHolder(binding: ItemPostEmptyBinding) :
        RecyclerView.ViewHolder(binding.root)
}