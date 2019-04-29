package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.model.LeitnerDay
import com.mrb.remember.domain.repository.ConfigRepository
import javax.inject.Inject

class GetCompletedDay @Inject constructor(private val repository: ConfigRepository) :
    UseCase<LeitnerDay, UseCase.None>() {

    override suspend fun run(params: None) = repository.completedDay()
}

class SaveCompletedDay @Inject constructor(private val repository: ConfigRepository) :
    UseCase<LeitnerDay, SaveCompletedDay.Params>() {

    override suspend fun run(params: Params) = repository.saveCompletedDay(params.day)

    data class Params(val day: LeitnerDay)
}