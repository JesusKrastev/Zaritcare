package com.zaritcare.data.room.advice

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AdviceDao {
    @Insert
    suspend fun insert(adviceEntity: AdviceEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(adviceEntity: AdviceEntity)
    @Query("SELECT COUNT(*) FROM tips")
    suspend fun count(): Int
    @Delete
    suspend fun delete(adviceEntity: AdviceEntity)
    @Query("SELECT * FROM tips")
    suspend fun get(): List<AdviceEntity>
    @Query("SELECT * FROM tips WHERE id = :id")
    suspend fun get(id: Int): AdviceEntity
}