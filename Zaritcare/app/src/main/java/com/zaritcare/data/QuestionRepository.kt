package com.zaritcare.data

import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.models.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionDao: QuestionDao
) {
    suspend fun insert(question: Question) = withContext(Dispatchers.IO) {
        questionDao.insert(question.toQuestionEntity())
    }

    suspend fun update(question: Question) = withContext(Dispatchers.IO) {
        questionDao.update(question.toQuestionEntity())
    }

    suspend fun count() = withContext(Dispatchers.IO) {
        questionDao.count()
    }

    suspend fun delete(question: Question) = withContext(Dispatchers.IO) {
        questionDao.delete(question.toQuestionEntity())
    }

    suspend fun get() = withContext(Dispatchers.IO) {
        questionDao.get().map { it.toQuestion() }
    }

    suspend fun getByCategory(category: String) = withContext(Dispatchers.IO) {
        questionDao.getByCategory(category).map { it.toQuestion() }
    }

    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        questionDao.get(id).toQuestion()
    }
}