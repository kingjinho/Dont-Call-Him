package com.kingjinho.dontcallhim.db.entity

import android.telephony.PhoneNumberUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kingjinho.dontcallhim.utils.Constant
import java.util.Locale

@Entity(tableName = Constant.TABLE_NAME_PHONE_NUMBER)
data class PhoneNumber(

    @PrimaryKey
    val number: String
) {
    val formattedNumber: String
        get() = PhoneNumberUtils.formatNumber(number, Locale.KOREA.country)

}