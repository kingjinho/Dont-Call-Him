package com.kingjinho.dontcallhim.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import com.kingjinho.dontcallhim.databinding.ItemPhoneNumberBinding
import com.kingjinho.dontcallhim.db.entity.PhoneNumber

private val diffUtil = object : ItemCallback<PhoneNumber>() {

    override fun areItemsTheSame(oldItem: PhoneNumber, newItem: PhoneNumber): Boolean {
        return oldItem.number == newItem.number
    }

    override fun areContentsTheSame(oldItem: PhoneNumber, newItem: PhoneNumber): Boolean {
        return oldItem.number == newItem.number
    }
}

class PhoneNumbersAdapter : ListAdapter<PhoneNumber, PhoneNumberViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneNumberViewHolder {
        return PhoneNumberViewHolder(
            ItemPhoneNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhoneNumberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}