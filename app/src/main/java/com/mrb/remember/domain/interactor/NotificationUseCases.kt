package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.repository.ConfigRepository
import javax.inject.Inject

class GetNotificationEnable @Inject constructor(private val repository: ConfigRepository) :
  UseCase<Boolean, UseCase.None>() {

  override suspend fun run(params: None) = repository.isNotificationEnabled()
}

class SaveNotificationEnable @Inject constructor(private val repository: ConfigRepository) :
  UseCase<Boolean, SaveNotificationEnable.Params>() {

  override suspend fun run(params: Params) = repository.saveNotificationEnable(params.enabled)

  data class Params(val enabled: Boolean)
}
