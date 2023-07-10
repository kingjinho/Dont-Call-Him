package com.kingjinho.dontcallhim.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kingjinho.dontcallhim.utils.Constant

@Entity(tableName = Constant.TABLE_NAME_PHONE_NUMBER)
data class PhoneNumber(

    @PrimaryKey
    val number: String
)