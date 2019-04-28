package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.model.User
import com.mrb.remember.domain.repository.UserRepository
import javax.inject.Inject

class GetUser @Inject constructor(private val repository: UserRepository) :
  UseCase<User, UseCase.None>() {

  override suspend fun run(params: None) = repository.user()
}