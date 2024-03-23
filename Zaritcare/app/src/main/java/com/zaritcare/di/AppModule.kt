package com.zaritcare.di

import android.content.Context
import com.zaritcare.data.room.ZaritcareDb
import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.advice.AdviceDao
import com.zaritcare.data.room.answer.AnswerDao
import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.data.room.record.RecordDao
import com.zaritcare.models.Advice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideActivityDao(
        db: ZaritcareDb
    ) : ActivityDao = db.activityDao()

    @Provides
    @Singleton
    fun provideAdviceDao(
        db: ZaritcareDb
    ) : AdviceDao = db.adviceDao()

    @Provides
    @Singleton
    fun provideAnswerDao(
        db: ZaritcareDb
    ) : AnswerDao = db.answerDao()

    @Provides
    @Singleton
    fun provideEmotionDao(
        db: ZaritcareDb
    ) : EmotionDao = db.emotionDao()

    @Provides
    @Singleton
    fun provideQuestionDao(
        db: ZaritcareDb
    ) : QuestionDao = db.questionDao()

    @Provides
    @Singleton
    fun provideRecordDao(
        db: ZaritcareDb
    ) : RecordDao = db.recordDao()

    @Provides
    @Singleton
    fun provideAgendaDatabase(
        @ApplicationContext context: Context
    ) : ZaritcareDb = ZaritcareDb.getDatabase(context)
}