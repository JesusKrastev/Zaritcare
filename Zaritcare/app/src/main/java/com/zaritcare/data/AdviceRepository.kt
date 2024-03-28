package com.zaritcare.data

import com.zaritcare.data.room.advice.AdviceDao
import com.zaritcare.models.Advice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AdviceRepository @Inject constructor(
    private val adviceDataSource: AdviceDao
) {
    suspend fun insert(advice: Advice) = withContext(Dispatchers.IO) {
        adviceDataSource.insert(advice.toAdviceEntity())
    }
    suspend fun update(advice: Advice) = withContext(Dispatchers.IO) {
        adviceDataSource.update(advice.toAdviceEntity())
    }
    suspend fun count() = withContext(Dispatchers.IO) {
        adviceDataSource.count()
    }
    suspend fun delete(advice: Advice) = withContext(Dispatchers.IO) {
        adviceDataSource.delete(advice.toAdviceEntity())
    }
    suspend fun get() = withContext(Dispatchers.IO) {
        adviceDataSource.get().map { it.toAdvice() }
    }
    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        adviceDataSource.get(id).toAdvice()
    }
}