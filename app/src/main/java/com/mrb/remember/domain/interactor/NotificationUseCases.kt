package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.repository.ConfigRepository
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class GetNotificationEnable @Inject constructor(private val repository: ConfigRepository) :
    UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None) = repository.isNotificationEnabled()
}

@OpenForTesting
class SaveNotificationEnable @Inject constructor(private val repository: ConfigRepository) :
    UseCase<Boolean, SaveNotificationEnable.Params>() {

    override suspend fun run(params: Params) = repository.saveNotificationEnable(params.enabled)

    data class Params(val enabled: Boolean)
}
