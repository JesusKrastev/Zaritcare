package com.zaritcare.data.room.category

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun get(): List<CategoryEntity>
    @Query("SELECT * FROM categories WHERE name = :name")
    suspend fun get(name: String): CategoryEntity
    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun get(id: Int): CategoryEntity
}