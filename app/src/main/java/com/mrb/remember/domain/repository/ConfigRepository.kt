package com.mrb.remember.domain.repository

import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.Hour
import com.mrb.remember.domain.model.LeitnerDay
import java.util.Date

interface ConfigRepository {

    fun completedDay(): Either<Failure, LeitnerDay>

    fun saveCompletedDay(day: LeitnerDay): Either<Failure, LeitnerDay>

    fun getNextStudyTime(): Either<Failure, Date>

    fun getStudyHour(): Either<Failure, Hour>

    fun saveStudyHour(hour: Hour): Either<Failure, Hour>

    fun saveNotificationEnable(isEnable: Boolean): Either<Failure, Boolean>

    fun isNotificationEnabled(): Either<Failure, Boolean>
}