package com.kingjinho.dontcallhim.repository

import com.kingjinho.dontcallhim.db.AppDatabase

class FetchNumbersRepo(private val db: AppDatabase) {

    fun fetchPhoneNumbers() = db.phoneNumberDao().getAll()

}