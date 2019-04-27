package com.mrb.remember.presentation.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.mrb.remember.presentation.platform.BaseViewModel
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class SplashViewModel
@Inject constructor() : BaseViewModel() {

  var isFirstStart: MutableLiveData<Boolean> = MutableLiveData()

  fun init() {
    Handler().postDelayed(
      {
        isFirstStart.value = false
      },
      1000
    )
  }

}


