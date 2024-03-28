package com.zaritcare.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zaritcare.data.room.activity.ActivityDao
import com.zaritcare.data.room.activity.ActivityEntity
import com.zaritcare.data.room.advice.AdviceDao
import com.zaritcare.data.room.advice.AdviceEntity
import com.zaritcare.data.room.answer.AnswerDao
import com.zaritcare.data.room.answer.AnswerEntity
import com.zaritcare.data.room.emotion.EmotionDao
import com.zaritcare.data.room.emotion.EmotionEntity
import com.zaritcare.data.room.question.QuestionDao
import com.zaritcare.data.room.question.QuestionEntity
import com.zaritcare.data.room.song.SongDao
import com.zaritcare.data.room.song.SongEntity

@Database(
    entities = [ActivityEntity::class, AdviceEntity::class, AnswerEntity::class, EmotionEntity::class, QuestionEntity::class, SongEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class ZaritcareDb: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun answerDao(): AnswerDao
    abstract fun emotionDao(): EmotionDao
    abstract fun questionDao(): QuestionDao
    abstract fun adviceDao(): AdviceDao
    abstract fun songDao(): SongDao

    companion object {
        fun getDatabase(context: Context) = Room.databaseBuilder(
            context,
            ZaritcareDb::class.java, "zaritcare-db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}