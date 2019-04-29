package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.model.Hour
import com.mrb.remember.domain.repository.ConfigRepository
import com.mrb.remember.testing.OpenForTesting
import java.util.Date
import javax.inject.Inject

@OpenForTesting
class GetNextStudyTime @Inject constructor(private val repository: ConfigRepository) :
    UseCase<Date, UseCase.None>() {

    override suspend fun run(params: None) = repository.getNextStudyTime()
}

@OpenForTesting
class GetStudyHour @Inject constructor(private val repository: ConfigRepository) :
    UseCase<Hour, UseCase.None>() {

    override suspend fun run(params: None) = repository.getStudyHour()
}

@OpenForTesting
class SaveStudyHour @Inject constructor(private val repository: ConfigRepository) :
    UseCase<Hour, SaveStudyHour.Params>() {

    override suspend fun run(params: Params) = repository.saveStudyHour(params.hour)

    data class Params(val hour: Hour)
}
