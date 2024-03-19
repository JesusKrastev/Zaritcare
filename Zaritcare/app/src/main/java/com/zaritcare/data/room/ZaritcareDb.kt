package com.zaritcare.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [ActivityEntity::class, AdviceEntity::class, AnswerEntity::class, EmotionEntity::class, QuestionEntity::class, RecordEntity::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(RoomConverters::class)
abstract class ZaritcareDb: RoomDatabase() {
    abstract fun activityDao(): ActivityDao
    abstract fun adviceDao(): AdviceDao
    abstract fun answerDao(): AnswerDao
    abstract fun emotionDao(): EmotionDao
    abstract fun questionDao(): QuestionDao
    abstract fun recordDao(): RecordDao
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