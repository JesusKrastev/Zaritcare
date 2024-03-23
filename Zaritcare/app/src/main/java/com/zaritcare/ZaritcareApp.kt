package com.zaritcare

import android.app.Application
import com.zaritcare.data.mocks.activity.ActivityDaoMock
import com.zaritcare.data.mocks.emotion.EmotionDaoMock
import com.zaritcare.data.mocks.question.QuestionDaoMock
import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.data.toActivity
import com.zaritcare.data.toActivityEntity
import com.zaritcare.data.toEmotion
import com.zaritcare.data.toEmotionEntity
import com.zaritcare.data.toQuestion
import com.zaritcare.data.toQuestionEntity
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

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

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            if (activityDaoMock.count() == 0){
                activityDaoMock.get().forEach { activity ->
                    activityDaoEntity.insert(activity.toActivity().toActivityEntity())
                }
            }
            if (emotionDaoMock.count() == 0){
                emotionDaoMock.get().forEach { emotion ->
                    emotionDaoEntity.insert(emotion.toEmotion().toEmotionEntity())
                }
            }
            if (questionDaoMock.count() == 0){
                questionDaoMock.get().forEach { question ->
                    questionDaoEntity.insert(question.toQuestion().toQuestionEntity())
                }
            }
        }
    }
}