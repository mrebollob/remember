package com.mrb.remember.domain.repository

import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.Hour
import com.mrb.remember.domain.model.LeitnerDay
import java.util.Date

interface ConfigRepository {

  suspend fun completedDay(): Either<Failure, LeitnerDay>

  suspend fun saveCompletedDay(day: LeitnerDay): Either<Failure, LeitnerDay>

  suspend fun getNextStudyTime(): Either<Failure, Date>

  suspend fun getStudyHour(): Either<Failure, Hour>

  suspend fun saveStudyHour(hour: Hour): Either<Failure, Hour>

  suspend fun saveNotificationEnable(isEnable: Boolean): Either<Failure, Boolean>

  suspend fun isNotificationEnabled(): Either<Failure, Boolean>
}