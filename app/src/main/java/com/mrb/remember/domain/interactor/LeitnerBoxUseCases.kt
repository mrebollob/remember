package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.Homework
import com.mrb.remember.domain.model.Question
import com.mrb.remember.domain.repository.RememberRepository
import javax.inject.Inject

class GetHomework @Inject constructor(private val repository: RememberRepository) :
    UseCase<Homework, GetHomework.Params>() {

    override suspend fun run(params: Params) = repository.homework(params.day)

    data class Params(val day: Int)
}

class GetQuestions @Inject constructor(private val repository: RememberRepository) :
    UseCase<List<Question>, GetQuestions.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<Question>> {
        return repository.questions(params.level)
    }

    data class Params(val level: Int)
}
