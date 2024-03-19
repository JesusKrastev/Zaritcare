package com.zaritcare.data.mocks.question

import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.zaritcare.data.room.question.QuestionEntity

class QuestionDaoMock {
    private val questions: MutableList<QuestionMock> = mutableListOf(
        QuestionMock(
            id = 1,
            question = "¿Cómo te sientes hoy?",
            category = QuestionMock.Category.WellBeing
        ),
        QuestionMock(
            id = 2,
            question = "Fatiga",
            category = QuestionMock.Category.WellBeing
        ),
        QuestionMock(
            id = 3,
            question = "Fatiga",
            category = QuestionMock.Category.WellBeing
        ),
        QuestionMock(
            id = 4,
            question = "Insomnio",
            category = QuestionMock.Category.WellBeing
        ),
        QuestionMock(
            id = 5,
            question = "Dolor de cabeza",
            category = QuestionMock.Category.WellBeing
        ),
        QuestionMock(
            id = 6,
            question = "Depresión",
            category = QuestionMock.Category.WellBeing
        ),
        QuestionMock(
            id = 7,
            question = "¿Sientes que tu familiar dependiente te está controlando demasiado?",
            category = QuestionMock.Category.Zarit
        ),
        QuestionMock(
            id = 8,
            question = "¿Sientes que tu vida social ha sido restringida debido a tu situación de cuidador?",
            category = QuestionMock.Category.Zarit
        ),
        QuestionMock(
            id = 9,
            question = "¿Te sientes frustado/a por la falta de apoyo de otras personas en el cuidado de tu familiar?",
            category = QuestionMock.Category.Zarit
        ),
        QuestionMock(
            id = 10,
            question = "¿Te sientes tenso/a entre tú y tu familiar dependiente?",
            category = QuestionMock.Category.Zarit
        ),
        QuestionMock(
            id = 11,
            question = "¿Te sientes culpable por no poder hacer más por tu familiar?",
            category = QuestionMock.Category.Zarit
        ),
        QuestionMock(
            id = 12,
            question = "¿Sientes que tu salud ha sufrido debido a tu situación de cuidador?",
            category = QuestionMock.Category.Zarit
        )
    )

    fun insert(question: QuestionMock) = questions.add(question)
    fun update(newQuestion: QuestionMock) {
        val index: Int = questions.indexOfFirst { it.id == newQuestion.id }
        questions[index] = newQuestion
    }
    fun count() = questions.size
    fun delete(question: QuestionMock) = questions.remove(question)
    fun get(): List<QuestionMock> = questions
    fun get(id: Int): QuestionMock? = questions.find { it.id == id }
}