package com.kingjinho.dontcallhim.database

object FakeDatabase {
    val dao = FakeDao()

    fun clearAll() {
        dao.clearAll()
    }
}