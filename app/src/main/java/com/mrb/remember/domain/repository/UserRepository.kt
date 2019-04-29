package com.mrb.remember.domain.repository

import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.User

interface UserRepository {

  fun user(): Either<Failure, User>
}