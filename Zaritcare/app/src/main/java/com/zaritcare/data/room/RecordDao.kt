package com.zaritcare.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecordDao {
    @Insert
    suspend fun insert(record: RecordEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(record: RecordEntity)
    @Query("SELECT COUNT(*) FROM activities")
    suspend fun count(): Int
    @Delete
    suspend fun delete(record: RecordEntity)
    @Query("SELECT * FROM records")
    suspend fun get(): List<RecordEntity>
    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun get(id: Int): RecordEntity
}