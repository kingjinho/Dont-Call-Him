package com.kingjinho.dontcallhim.recyclerview

import android.telephony.PhoneNumberUtils
import androidx.recyclerview.widget.RecyclerView
import com.kingjinho.dontcallhim.databinding.ItemPhoneNumberBinding
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import com.kingjinho.dontcallhim.utils.isValidPhoneNumber
import java.util.Locale

class PhoneNumberViewHolder(
    private val binding: ItemPhoneNumberBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhoneNumber) {
        val phoneNumber = PhoneNumberUtils.formatNumber(item.number, Locale.KOREA.country)
        if (phoneNumber.isValidPhoneNumber()) {
            binding.phoneNumber.text = phoneNumber
        }
    }

}