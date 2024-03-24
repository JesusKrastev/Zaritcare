package com.zaritcare.data

import com.zaritcare.data.mocks.activity.ActivityMock
import com.zaritcare.data.mocks.advice.AdviceMock
import com.zaritcare.data.mocks.category.CategoryMock
import com.zaritcare.data.mocks.emotion.EmotionMock
import com.zaritcare.data.mocks.question.QuestionMock
import com.zaritcare.data.room.activity.ActivityEntity
import com.zaritcare.data.room.advice.AdviceEntity
import com.zaritcare.data.room.answer.AnswerEntity
import com.zaritcare.data.room.category.CategoryDao
import com.zaritcare.data.room.category.CategoryEntity
import com.zaritcare.data.room.emotion.EmotionEntity
import com.zaritcare.data.room.question.QuestionEntity
import com.zaritcare.models.Activity
import com.zaritcare.models.Advice
import com.zaritcare.models.Answer
import com.zaritcare.models.Category
import com.zaritcare.models.Emotion
import com.zaritcare.models.Question

fun ActivityMock.toActivity() = Activity(
    id = id,
    image = image,
    name = name,
    description = description,
    authorQuote = authorQuote,
    quote = quote,
    action = action
)

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

fun CategoryMock.toCategory() = Category(
    id = id,
    name = name
)

fun CategoryEntity.toCategory() = Category(
    id = id,
    name = name
)

fun Category.toCategoryEntity() = CategoryEntity(
    id = id,
    name = name
)

fun Question.QuestionType.toQuestionTypeMock() = when(this) {
    Question.QuestionType.EMOTION -> QuestionMock.QuestionType.EMOTION
    Question.QuestionType.RANGE -> QuestionMock.QuestionType.RANGE
}

fun QuestionMock.QuestionType.toQuestionType() = when(this) {
    QuestionMock.QuestionType.EMOTION -> Question.QuestionType.EMOTION
    QuestionMock.QuestionType.RANGE -> Question.QuestionType.RANGE
}

suspend fun QuestionEntity.toQuestion(categoryDao: CategoryDao) = Question(
    id = id,
    question = question,
    category = categoryDao.get(category).name,
    type = Question.QuestionType.valueOf(type.uppercase()),
    minimumValueIndicator = minimumValueIndicator,
    maximumValueIndicator = maximumValueIndicator
)

suspend fun Question.toQuestionEntity(categoryDao: CategoryDao) = QuestionEntity(
    id = id,
    question = question,
    category = categoryDao.get(category).id,
    type = type.name,
    minimumValueIndicator = minimumValueIndicator,
    maximumValueIndicator = maximumValueIndicator
)

suspend fun QuestionMock.toQuestion(categoryDao: CategoryDao) = Question(
    id = id,
    question = question,
    category = categoryDao.get(category).name,
    type = type.toQuestionType(),
    minimumValueIndicator = minimumValueIndicator,
    maximumValueIndicator = maximumValueIndicator
)