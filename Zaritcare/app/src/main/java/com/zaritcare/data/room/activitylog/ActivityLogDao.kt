package com.zaritcare.data.room.activitylog

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

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
    suspend fun get(): Flow<List<ActivityLogEntity>>
    @Query("SELECT * FROM activity_logs WHERE id = :id")
    suspend fun get(id: Int): ActivityLogEntity?
}