package com.zaritcare.data.mocks.question

import com.zaritcare.models.Category
import com.zaritcare.models.Type
import javax.inject.Inject

class QuestionDaoMock @Inject constructor() {
    private val questions: MutableList<QuestionMock> = mutableListOf(
        QuestionMock(
            id = 1,
            question = "¿Cómo te sientes hoy?",
            category = Category.BIENESTAR.name,
            type =  Type.EMOTION.name,
            minimumValueIndicator = "No severo",
            maximumValueIndicator = "Severo"
        ),
        QuestionMock(
            id = 2,
            question = "Fatiga",
            category = Category.BIENESTAR.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "No severo",
            maximumValueIndicator = "Severo"
        ),
        QuestionMock(
            id = 3,
            question = "Insomnio",
            category = Category.BIENESTAR.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "No severo",
            maximumValueIndicator = "Severo"
        ),
        QuestionMock(
            id = 4,
            question = "Dolor de cabeza",
            category = Category.BIENESTAR.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "No severo",
            maximumValueIndicator = "Severo"
        ),
        QuestionMock(
            id = 5,
            question = "Depresión",
            category = Category.BIENESTAR.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "No severo",
            maximumValueIndicator = "Severo"
        ),
        QuestionMock(
            id = 6,
            question = "¿Sientes que tu familiar dependiente te está controlando demasiado?",
            category = Category.ZARIT.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "Nunca",
            maximumValueIndicator = "Casi siempre"
        ),
        QuestionMock(
            id = 7,
            question = "¿Sientes que tu vida social ha sido restringida debido a tu situación de cuidador?",
            category = Category.ZARIT.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "Nunca",
            maximumValueIndicator = "Casi siempre"
        ),
        QuestionMock(
            id = 8,
            question = "¿Te sientes frustado/a por la falta de apoyo de otras personas en el cuidado de tu familiar?",
            category = Category.ZARIT.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "Nunca",
            maximumValueIndicator = "Casi siempre"
        ),
        QuestionMock(
            id = 9,
            question = "¿Te sientes tenso/a entre tú y tu familiar dependiente?",
            category = Category.ZARIT.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "Nunca",
            maximumValueIndicator = "Casi siempre"
        ),
        QuestionMock(
            id = 10,
            question = "¿Te sientes culpable por no poder hacer más por tu familiar?",
            category = Category.ZARIT.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "Nunca",
            maximumValueIndicator = "Casi siempre"
        ),
        QuestionMock(
            id = 11,
            question = "¿Sientes que tu salud ha sufrido debido a tu situación de cuidador?",
            category = Category.ZARIT.name,
            type =  Type.RANGE.name,
            minimumValueIndicator = "Nunca",
            maximumValueIndicator = "Casi siempre"
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