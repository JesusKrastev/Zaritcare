package com.zaritcare.data.room.question

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert
    suspend fun insert(questionEntity: QuestionEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(questionEntity: QuestionEntity)
    @Query("SELECT COUNT(*) FROM questions")
    suspend fun count(): Int
    @Delete
    suspend fun delete(questionEntity: QuestionEntity)
    @Query("SELECT * FROM questions")
    suspend fun get(): List<QuestionEntity>
    @Query("SELECT * FROM questions WHERE category = :category")
    suspend fun getByCategory(category: String): List<QuestionEntity>
    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun get(id: Int): QuestionEntity
}