package com.zaritcare.data

import com.zaritcare.data.room.answer.AnswerDao
import com.zaritcare.data.room.answer.AnswerEntity
import com.zaritcare.models.Answer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnswerRepository @Inject constructor(
    private val dao: AnswerDao
) {
    suspend fun insert(answer: Answer) = withContext(Dispatchers.IO) {
        dao.insert(answer.toAnswerEntity())
    }

    suspend fun update(answer: Answer) = withContext(Dispatchers.IO) {
        dao.update(answer.toAnswerEntity())
    }

    suspend fun count() = withContext(Dispatchers.IO) {
        dao.count()
    }

    suspend fun delete(answer: Answer) = withContext(Dispatchers.IO) {
        dao.delete(answer.toAnswerEntity())
    }

    suspend fun get() = withContext(Dispatchers.IO) {
        dao.get().map { it.toAnswer() }
    }

    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        dao.get(id).toAnswer()
    }
}