package com.mirkamol.delegation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mirkamol.delegation.databinding.ItemHomeBinding
import com.mirkamol.delegation.model.User

class HomeAdapter(private var listener: (() -> Unit)) :
    ListAdapter<User, HomeAdapter.Vh>(MyDiffUtil()) {


    inner class Vh(
        private var binding: ItemHomeBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User) {
            binding.apply {
                tvTitle.text = user.title
                tvDescription.text = user.description
                Glide.with(root.context).load(user.image).into(ivImage)

                ivImage.setOnClickListener {
                    listener.invoke()
                }
            }
        }
    }

    class MyDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title &&
                    oldItem.description == newItem.description
            oldItem.image = newItem.image


        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position))
    }


}