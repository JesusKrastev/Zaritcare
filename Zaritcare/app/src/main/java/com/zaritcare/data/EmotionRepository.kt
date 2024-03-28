package com.zaritcare.data

import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.models.Emotion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmotionRepository @Inject constructor(
    private val dao: EmotionDao
) {
    suspend fun insert(emotion: Emotion) = withContext(Dispatchers.IO) {
        dao.insert(emotion.toEmotionEntity())
    }
    suspend fun update(emotion: Emotion) = withContext(Dispatchers.IO) {
        dao.update(emotion.toEmotionEntity())
    }
    suspend fun count() = withContext(Dispatchers.IO) {
        dao.count()
    }
    suspend fun delete(emotion: Emotion) = withContext(Dispatchers.IO) {
        dao.delete(emotion.toEmotionEntity())
    }
    suspend fun get() = withContext(Dispatchers.IO) {
        dao.get().map { it.toEmotion() }
    }
    suspend fun get(id: Int) = withContext(Dispatchers.IO) {
        dao.get(id).toEmotion()
    }
    suspend fun get(name: String) = withContext(Dispatchers.IO) {
        dao.get(name).toEmotion()
    }
}