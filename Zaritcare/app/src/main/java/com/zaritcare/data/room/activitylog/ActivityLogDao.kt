package com.zaritcare.data.room.activitylog

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ActivityLogDao {
    @Insert
    suspend fun insert(activityLog: ActivityLogEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(activityLog: ActivityLogEntity)
    @Query("SELECT COUNT(*) FROM activity_logs")
    suspend fun count(): Int
    @Delete
    suspend fun delete(activityLog: ActivityLogEntity)
    @Query("SELECT * FROM activity_logs")
    suspend fun get():  List<ActivityLogEntity>
    @Query("SELECT * FROM activity_logs WHERE id = :id")
    suspend fun get(id: Int): ActivityLogEntity?
    @Query("SELECT * FROM activity_logs WHERE date = :date AND user = :user")
    fun get(date: LocalDate, user: Int): Flow<List<ActivityLogEntity>>
}