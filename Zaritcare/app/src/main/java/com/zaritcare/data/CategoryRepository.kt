package com.zaritcare.data

import com.zaritcare.data.room.category.CategoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val dao: CategoryDao
) {
    suspend fun get() = withContext(Dispatchers.IO) {
        dao.get().map { it.toCategory() }
    }
    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        dao.get(id).toCategory()
    }
}