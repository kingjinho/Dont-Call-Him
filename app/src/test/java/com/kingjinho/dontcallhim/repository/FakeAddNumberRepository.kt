package com.kingjinho.dontcallhim.repository

import com.kingjinho.dontcallhim.database.FakeDatabase
import com.kingjinho.dontcallhim.db.entity.PhoneNumber

class FakeAddNumberRepository(private val database: FakeDatabase) {

    suspend fun addNumber(number: String) {
        database.dao.insertNumber(PhoneNumber(number = number))
    }

}