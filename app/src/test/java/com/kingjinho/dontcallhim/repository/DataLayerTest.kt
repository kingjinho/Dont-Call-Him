package com.kingjinho.dontcallhim.repository

import com.google.common.truth.Truth.assertThat
import com.kingjinho.dontcallhim.database.FakeDatabase
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class DataLayerTest {

    private lateinit var addRepo: FakeAddNumberRepository
    private lateinit var fetchRepo: FakeFetchNumbersRepository
    private lateinit var database: FakeDatabase


    @Before
    fun setup() {
        database = FakeDatabase
        addRepo = FakeAddNumberRepository(database)
        fetchRepo = FakeFetchNumbersRepository(database)

        runTest {
            database.dao.insertNumber(PhoneNumber(number = "01000000000"))
            database.dao.insertNumber(PhoneNumber(number = "01000000001"))
            database.dao.insertNumber(PhoneNumber(number = "01000000002"))
            database.dao.insertNumber(PhoneNumber(number = "01000000003"))
        }
    }

    @After
    fun clear() {
        database.clearAll()
    }

    @Test
    fun `fetchPhoneNumbers()_returns 4 phone numbers`() = runTest {
        val result = fetchRepo.fetchPhoneNumbers().single()
        assertThat(result.size).isEqualTo(4)
    }

    @Test
    fun `getExistingNumber()_with 01000000000_returns true`() = runTest {
        val result = addRepo.numberExists("01000000000")
        assertThat(result).isTrue()
    }

    @Test
    fun `getExistingNumber()_with 01000001234_returns false`() = runTest {
        val result = addRepo.numberExists("01000001234")
        assertThat(result).isFalse()
    }


}