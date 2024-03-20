package com.zaritcare.data

import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.models.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val dao: QuestionDao
) {
    suspend fun insert(question: Question) = withContext(Dispatchers.IO) {
        dao.insert(question.toQuestionEntity())
    }
    suspend fun update(question: Question) = withContext(Dispatchers.IO) {
        dao.update(question.toQuestionEntity())
    }
    suspend fun count() = withContext(Dispatchers.IO) {
        dao.count()
    }
    suspend fun delete(question: Question) = withContext(Dispatchers.IO) {
        dao.delete(question.toQuestionEntity())
    }
    suspend fun get() = withContext(Dispatchers.IO) {
        dao.get().map { it.toQuestion() }
    }
    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        dao.get(id).toQuestion()
    }
}