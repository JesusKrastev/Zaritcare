package com.zaritcare

import android.app.Application
import android.util.Log
import com.zaritcare.data.QuestionRepository
import com.zaritcare.data.mocks.activity.ActivityDaoMock
import com.zaritcare.data.mocks.category.CategoryDaoMock
import com.zaritcare.data.mocks.emotion.EmotionDaoMock
import com.zaritcare.data.mocks.question.QuestionDaoMock
import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.category.CategoryDao
import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.data.room.question.QuestionEntity
import com.zaritcare.data.toActivity
import com.zaritcare.data.toActivityEntity
import com.zaritcare.data.toCategory
import com.zaritcare.data.toCategoryEntity
import com.zaritcare.data.toEmotion
import com.zaritcare.data.toEmotionEntity
import com.zaritcare.data.toQuestion
import com.zaritcare.data.toQuestionEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class ZaritcareApp: Application() {
    @Inject
    lateinit var activityDaoMock: ActivityDaoMock
    @Inject
    lateinit var activityDaoEntity: ActivityDao
    @Inject
    lateinit var emotionDaoMock: EmotionDaoMock
    @Inject
    lateinit var emotionDaoEntity: EmotionDao
    @Inject
    lateinit var questionDaoMock: QuestionDaoMock
    @Inject
    lateinit var questionDaoEntity: QuestionDao
    @Inject
    lateinit var categoryDaoMock: CategoryDaoMock
    @Inject
    lateinit var categoryDaoEntity: CategoryDao

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if(categoryDaoEntity.count() == 0){
                categoryDaoMock.get().forEach { category ->
                    categoryDaoEntity.insert(category.toCategory().toCategoryEntity())
                }
            }
            if (activityDaoEntity.count() == 0){
                activityDaoMock.get().forEach { activity ->
                    activityDaoEntity.insert(activity.toActivity().toActivityEntity())
                }
            }
            if (emotionDaoEntity.count() == 0){
                emotionDaoMock.get().forEach { emotion ->
                    emotionDaoEntity.insert(emotion.toEmotion().toEmotionEntity())
                }
            }
            if (questionDaoEntity.count() == 0){
                questionDaoMock.get().forEach { question ->
                    questionDaoEntity.insert(question.toQuestion(categoryDaoEntity).toQuestionEntity(categoryDaoEntity))
                }
            }
        }
    }
}