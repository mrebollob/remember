package com.mrb.remember.presentation.main.profile

import androidx.lifecycle.MutableLiveData
import com.mrb.remember.domain.interactor.GetUser
import com.mrb.remember.domain.interactor.UseCase
import com.mrb.remember.domain.model.User
import com.mrb.remember.presentation.platform.BaseViewModel
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class ProfileViewModel @Inject constructor(private val getUser: GetUser) : BaseViewModel() {

  var userData: MutableLiveData<User> = MutableLiveData()

  fun init() {
    showLoading()
    getUser(UseCase.None()) {
      it.either(::handleFailure, ::handleUser)
    }
  }

  private fun handleUser(user: User) {
    hideLoading()
    this.userData.value = user
  }
}

