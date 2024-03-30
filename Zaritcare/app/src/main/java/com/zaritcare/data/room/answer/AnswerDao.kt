package com.zaritcare.data.room.answer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zaritcare.data.room.activitylog.ActivityLogEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface AnswerDao {
    @Insert
    suspend fun insert(answerEntity: AnswerEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(answerEntity: AnswerEntity)
    @Query("SELECT COUNT(*) FROM answers")
    suspend fun count(): Int
    @Delete
    suspend fun delete(answerEntity: AnswerEntity)
    @Query("SELECT * FROM answers")
    suspend fun get(): List<AnswerEntity>
    @Query("SELECT * FROM answers WHERE date = :date AND user = :user")
    fun get(date: LocalDate, user: Int): Flow<List<AnswerEntity>>
    @Query("SELECT * FROM answers WHERE id = :id")
    suspend fun get(id: Int): AnswerEntity
}