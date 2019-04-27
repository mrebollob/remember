package com.mrb.remember.presentation.splash

import android.os.Bundle
import com.mrb.remember.R
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.failure
import com.mrb.remember.domain.extension.observe
import com.mrb.remember.domain.extension.toast
import com.mrb.remember.domain.extension.viewModel
import com.mrb.remember.presentation.platform.BaseActivity
import com.mrb.remember.presentation.main.MainActivity
import com.mrb.remember.testing.OpenForTesting

@OpenForTesting
class SplashActivity : BaseActivity() {

  private lateinit var splashViewModel: SplashViewModel

  override fun layoutId() = R.layout.activity_splash
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    splashViewModel = viewModel(viewModelFactory) {
      observe(isFirstStart, ::renderResult)
      failure(failure, ::handleError)
    }

    splashViewModel.init()
  }

  private fun renderResult(isFirstStart: Boolean?) {
    initApp()
  }

  private fun initApp() {
    MainActivity.open(this)
    finish()
  }

  private fun handleError(failure: Failure?) {
    toast(getString(R.string.generic_error))
  }
}
