package com.kingjinho.dontcallhim.database

import com.kingjinho.dontcallhim.db.dao.PhoneNumberDao
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDao : PhoneNumberDao {

    private val database = mutableListOf<PhoneNumber>()

    override suspend fun insertNumber(phoneNumber: PhoneNumber) {
        database.add(phoneNumber)
    }

    override suspend fun getExistingData(number: String): Boolean {
        return database.any { it.number == number }
    }

    override fun getAll(): Flow<List<PhoneNumber>> = flow {
        emit(database.toList())
    }

}