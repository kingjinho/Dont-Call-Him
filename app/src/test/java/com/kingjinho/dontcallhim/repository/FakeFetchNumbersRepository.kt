package com.kingjinho.dontcallhim.repository

import com.kingjinho.dontcallhim.database.FakeDatabase

class FakeFetchNumbersRepository(private val database: FakeDatabase) {

    fun fetchPhoneNumbers() = database.dao.getAll()
}