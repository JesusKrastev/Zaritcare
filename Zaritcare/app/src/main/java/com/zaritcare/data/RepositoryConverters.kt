package com.zaritcare.data

import com.zaritcare.data.mocks.activity.ActivityMock
import com.zaritcare.data.mocks.advice.AdviceMock
import com.zaritcare.data.mocks.emotion.EmotionMock
import com.zaritcare.data.mocks.question.QuestionMock
import com.zaritcare.data.room.activity.ActivityEntity
import com.zaritcare.data.room.advice.AdviceEntity
import com.zaritcare.data.room.answer.AnswerEntity
import com.zaritcare.data.room.emotion.EmotionEntity
import com.zaritcare.data.room.question.QuestionEntity
import com.zaritcare.models.Activity
import com.zaritcare.models.Advice
import com.zaritcare.models.Answer
import com.zaritcare.models.Emotion
import com.zaritcare.models.Question

fun ActivityMock.toActivity() = Activity(
    id = id,
    frontPage = frontPage,
    banner = banner,
    title = title,
    description = description,
    authorQuote = authorQuote,
    quoteImage = quoteImage,
    quote = quote,
    actions = actions,
    action = action
)

fun ActivityEntity.toActivity() = Activity(
    id = id,
    frontPage = frontPage,
    banner = banner,
    title = title,
    description = description,
    authorQuote = authorQuote,
    quoteImage = quoteImage,
    quote = quote,
    actions = actions,
    action = action
)

fun Activity.toActivityEntity() = ActivityEntity(
    id = id,
    frontPage = frontPage,
    banner = banner,
    title = title,
    description = description,
    authorQuote = authorQuote,
    quoteImage = quoteImage,
    quote = quote,
    actions = actions,
    action = action
)

fun AdviceMock.toAdvice() = Advice(
    id = id,
    image = image,
    title = title,
    description = description
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

fun EmotionMock.toEmotion() = Emotion(
    id = id,
    image = image,
    name = name
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
    category = category,
    type = type,
    minimumValueIndicator = minimumValueIndicator,
    maximumValueIndicator = maximumValueIndicator
)

fun Question.toQuestionEntity() = QuestionEntity(
    id = id,
    question = question,
    category = category,
    type = type,
    minimumValueIndicator = minimumValueIndicator,
    maximumValueIndicator = maximumValueIndicator
)

fun QuestionMock.toQuestion() = Question(
    id = id,
    question = question,
    category = category,
    type = type,
    minimumValueIndicator = minimumValueIndicator,
    maximumValueIndicator = maximumValueIndicator
)