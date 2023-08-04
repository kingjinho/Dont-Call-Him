package com.kingjinho.dontcallhim.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kingjinho.dontcallhim.db.entity.PhoneNumber
import com.kingjinho.dontcallhim.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface PhoneNumberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(phoneNumber: PhoneNumber)

    @Query("""
       SELECT EXISTS(
                    SELECT *
                      FROM ${Constant.TABLE_NAME_PHONE_NUMBER} 
                     WHERE number = :number
                    )
    """)
    suspend fun getExistingData(number: String): Boolean

    @Query("SELECT * FROM ${Constant.TABLE_NAME_PHONE_NUMBER}")
    fun getAll(): Flow<List<PhoneNumber>>
}