package com.kingjinho.dontcallhim

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kingjinho.dontcallhim.db.AppDatabase
import com.kingjinho.dontcallhim.db.dao.PhoneNumberDao
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import com.kingjinho.dontcallhim.utils.isValidPhoneNumber
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: PhoneNumberDao
    private lateinit var db: AppDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = db.phoneNumberDao()
    }

    @After
    @Throws(IOException::class)
    fun close() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `addPhoneNumber_010-3333-3333_isAddedSuccessfully`() = runTest {
        val user = PhoneNumber("010-3333-3333")
        assertThat(user.number.isValidPhoneNumber()).isTrue()
        dao.insertNumber(user)

        val byNumber = dao.getExistingData("010-3333-3333")

        assertThat(byNumber).isTrue()
    }

    @Test
    @Throws(Exception::class)
    fun `addPhoneNumber_010-333-333_isNotAddedSuccessfully`() = runTest {
        val user = PhoneNumber("010-3333-333")
        assertThat(user.number.isValidPhoneNumber()).isTrue()
        dao.insertNumber(user)

        val byNumber = dao.getExistingData("010-3333-3333")

        assertThat(byNumber).isTrue()
    }

}