package com.mrb.remember.data.entity

import com.google.gson.annotations.SerializedName
import com.mrb.remember.domain.model.Question
import com.mrb.remember.domain.model.QuestionItem

data class QuestionEntity(
    @SerializedName("_id")
    private val id: String,
    private val question: QuestionItemEntity,
    private val response: QuestionItemEntity,
    private val level: Int,
    private val failures: Int
) {
    fun toQuestion() = Question(
        id,
        question.toQuestionItem(),
        response.toQuestionItem(),
        level,
        failures
    )
}

data class QuestionItemEntity(
    private val title: String,
    private val detail: String,
    private val imageUrl: String
) {
    fun toQuestionItem() = QuestionItem(title, detail, imageUrl)
}