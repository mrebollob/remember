package com.mrb.remember.domain.interactor

import com.mrb.remember.domain.model.User
import com.mrb.remember.domain.repository.UserRepository
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class GetUser @Inject constructor(private val repository: UserRepository) :
  UseCase<User, UseCase.None>() {

  override suspend fun run(params: None) = repository.user()
}