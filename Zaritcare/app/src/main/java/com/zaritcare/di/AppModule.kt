package com.zaritcare.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.zaritcare.data.EmotionRepository
import com.zaritcare.data.room.ZaritcareDb
import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.activitylog.ActivityLogDao
import com.zaritcare.data.room.advice.AdviceDao
import com.zaritcare.data.room.answer.AnswerDao
import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.data.room.song.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_PREFERENCES_NAME = "settings"
private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAppSettingsDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideActivityDao(
        db: ZaritcareDb
    ) : ActivityDao = db.activityDao()

    @Provides
    @Singleton
    fun provideSongDao(
        db: ZaritcareDb
    ) : SongDao = db.songDao()

    @Provides
    @Singleton
    fun provideActivityLogDao(
        db: ZaritcareDb
    ) : ActivityLogDao = db.activityLogDao()

    @Provides
    @Singleton
    fun provideAnswerDao(
        db: ZaritcareDb
    ) : AnswerDao = db.answerDao()

    @Provides
    @Singleton
    fun provideAdviceDao(
        db: ZaritcareDb
    ) : AdviceDao = db.adviceDao()

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
    fun provideAgendaDatabase(
        @ApplicationContext context: Context
    ) : ZaritcareDb = ZaritcareDb.getDatabase(context)

    @Provides
    @Singleton
    fun provideEmotionRepository(
        emotionDao: EmotionDao
    ) : EmotionRepository = EmotionRepository(emotionDao)

}