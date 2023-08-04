package com.kingjinho.dontcallhim.repository

import com.kingjinho.dontcallhim.db.AppDatabase
import javax.inject.Inject

class FetchNumbersRepo @Inject constructor(private val db: AppDatabase) {

    fun fetchPhoneNumbers() = db.phoneNumberDao().getAll()

}