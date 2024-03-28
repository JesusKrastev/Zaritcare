package com.zaritcare.data.room.emotion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EmotionDao {
    @Insert
    suspend fun insert(emotionEntity: EmotionEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(emotionEntity: EmotionEntity)
    @Query("SELECT COUNT(*) FROM emotions")
    suspend fun count(): Int
    @Delete
    suspend fun delete(emotionEntity: EmotionEntity)
    @Query("SELECT * FROM emotions")
    suspend fun get(): List<EmotionEntity>
    @Query("SELECT * FROM emotions WHERE id = :id")
    suspend fun get(id: Int): EmotionEntity
    @Query("SELECT * FROM emotions WHERE name = :name")
    suspend fun get(name: String): EmotionEntity
}