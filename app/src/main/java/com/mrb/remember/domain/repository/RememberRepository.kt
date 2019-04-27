package com.mrb.remember.domain.repository

import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.Homework
import com.mrb.remember.domain.model.Question

interface RememberRepository {

  suspend fun homework(day: Int): Either<Failure, Homework>

  suspend fun questions(level: Int): Either<Failure, List<Question>>
}