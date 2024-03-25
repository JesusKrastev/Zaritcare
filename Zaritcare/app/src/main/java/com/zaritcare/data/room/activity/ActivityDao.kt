package com.zaritcare.data.room.activity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ActivityDao {
    @Insert
    suspend fun insert(activity: ActivityEntity)
    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(activity: ActivityEntity)
    @Query("SELECT COUNT(*) FROM activities")
    suspend fun count(): Int
    @Delete
    suspend fun delete(activity: ActivityEntity)
    @Query("SELECT * FROM activities")
    suspend fun get(): List<ActivityEntity>
    @Query("SELECT * FROM activities WHERE id = :id")
    suspend fun get(id: Int): ActivityEntity?
}