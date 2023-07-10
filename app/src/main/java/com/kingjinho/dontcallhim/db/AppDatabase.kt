package com.kingjinho.dontcallhim.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kingjinho.dontcallhim.db.dao.PhoneNumberDao
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import com.kingjinho.dontcallhim.utils.Constant

@Database(
    version = 1,
    entities = [PhoneNumber::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun phoneNumberDao(): PhoneNumberDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                Constant.DB_NAME
            ).build()
            INSTANCE = instance
            instance
        }
    }
}