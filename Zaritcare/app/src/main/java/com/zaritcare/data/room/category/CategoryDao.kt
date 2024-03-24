package com.zaritcare.data.room.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zaritcare.data.room.question.QuestionEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun get(): List<CategoryEntity>
    @Query("SELECT * FROM categories WHERE name = :name")
    suspend fun get(name: String): CategoryEntity
    @Insert
    suspend fun insert(categoryEntity: CategoryEntity)
    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun get(id: Int): CategoryEntity
    @Query("SELECT COUNT(*) FROM categories")
    suspend fun count(): Int
}