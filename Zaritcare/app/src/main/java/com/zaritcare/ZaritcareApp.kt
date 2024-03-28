package com.zaritcare

import android.app.Application
import com.zaritcare.data.mocks.activity.ActivityDaoMock
import com.zaritcare.data.mocks.advice.AdviceDaoMock
import com.zaritcare.data.mocks.emotion.EmotionDaoMock
import com.zaritcare.data.mocks.question.QuestionDaoMock
import com.zaritcare.data.mocks.song.SongDaoMock
import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.advice.AdviceDao
import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.data.room.song.SongDao
import com.zaritcare.data.toActivity
import com.zaritcare.data.toActivityEntity
import com.zaritcare.data.toAdvice
import com.zaritcare.data.toAdviceEntity
import com.zaritcare.data.toEmotion
import com.zaritcare.data.toEmotionEntity
import com.zaritcare.data.toQuestion
import com.zaritcare.data.toQuestionEntity
import com.zaritcare.data.toSong
import com.zaritcare.data.toSongEntity
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
    lateinit var adviceDaoMock: AdviceDaoMock
    @Inject
    lateinit var adviceDaoEntity: AdviceDao
    @Inject
    lateinit var songDaoEntity: SongDao
    @Inject
    lateinit var songDaoMock: SongDaoMock

    override fun onCreate() {
        super.onCreate()
        runBlocking {
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
                    questionDaoEntity.insert(question.toQuestion().toQuestionEntity())
                }
            }
            if (adviceDaoEntity.count() == 0){
                adviceDaoMock.get().forEach { advice ->
                    adviceDaoEntity.insert(advice.toAdvice().toAdviceEntity())
                }
            }
            if(songDaoEntity.count() == 0){
                songDaoMock.get().forEach { song ->
                    songDaoEntity.insert(song.toSong().toSongEntity())
                }
            }
        }
    }
}