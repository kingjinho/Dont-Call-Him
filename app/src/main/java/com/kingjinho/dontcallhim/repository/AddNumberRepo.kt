package com.kingjinho.dontcallhim.repository

import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.db.entity.PhoneNumber

class AddNumberRepo(private val db: AppDatabase) {

    suspend fun addNumber(number: String) {
        db.phoneNumberDao().insertNumber(PhoneNumber(number = number))
    }
}