package com.zaritcare.data.room.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDao {
    @Insert
    suspend fun insert(songEntity: SongEntity)
    @Query("SELECT * FROM songs")
    suspend fun get(): List<SongEntity>
    @Query("SELECT * FROM songs WHERE id = :id")
    suspend fun get(id: Int): SongEntity
    @Query("SELECT COUNT(*) FROM songs")
    suspend fun count(): Int
}