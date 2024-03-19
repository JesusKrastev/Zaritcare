package com.zaritcare.data

import com.zaritcare.data.room.activity.ActivityEntity
import com.zaritcare.data.room.advice.AdviceEntity
import com.zaritcare.data.room.answer.AnswerEntity
import com.zaritcare.data.room.emotion.EmotionEntity
import com.zaritcare.data.room.question.QuestionEntity
import com.zaritcare.data.room.record.RecordEntity
import com.zaritcare.models.Activity
import com.zaritcare.models.Advice
import com.zaritcare.models.Answer
import com.zaritcare.models.Emotion
import com.zaritcare.models.Question
import com.zaritcare.models.Record

fun ActivityEntity.toActivity() = Activity(
    id = id,
    image = image,
    name = name,
    description = description,
    authorQuote = authorQuote,
    quote = quote,
    action = action
)

fun Activity.toActivityEntity() = ActivityEntity(
    id = id,
    image = image,
    name = name,
    description = description,
    authorQuote = authorQuote,
    quote = quote,
    action = action
)

fun AdviceEntity.toAdvice() = Advice(
    id = id,
    image = image,
    title = title,
    description = description
)

fun Advice.toAdviceEntity() = AdviceEntity(
    id = id,
    image = image,
    title = title,
    description = description
)

fun AnswerEntity.toAnswer() = Answer(
    id = id,
    question = question,
    answer = answer,
    user = user
)

fun EmotionEntity.toEmotion() = Emotion(
    id = id,
    image = image,
    name = name
)

fun Emotion.toEmotionEntity() = EmotionEntity(
    id = id,
    image = image,
    name = name
)

fun QuestionEntity.toQuestion() = Question(
    id = id,
    question = question,
    category = category
)

fun Question.toQuestionEntity() = QuestionEntity(
    id = id,
    question = question,
    category = category
)

fun RecordEntity.toRecord() = Record(
    id = id,
    activity = activity,
    answer = answer,
    realizationDate = realizationDate
)

fun Record.toRecordEntity() = RecordEntity(
    id = id,
    activity = activity,
    answer = answer,
    realizationDate = realizationDate
)