package com.mrb.remember.presentation.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.mrb.remember.domain.interactor.GetNotificationEnable
import com.mrb.remember.domain.interactor.GetStudyHour
import com.mrb.remember.domain.interactor.UseCase
import com.mrb.remember.notification.NotificationManager
import com.mrb.remember.presentation.platform.BaseViewModel
import com.mrb.remember.testing.OpenForTesting
import timber.log.Timber
import javax.inject.Inject

@OpenForTesting
class SplashViewModel @Inject constructor(
  private val getNotificationEnable: GetNotificationEnable,
  private val getStudyHour: GetStudyHour,
  private val notificationManager: NotificationManager
) : BaseViewModel() {

  var isFirstStart: MutableLiveData<Boolean> = MutableLiveData()

  fun init() {
    getNotificationEnable(UseCase.None()) {
      it.either(::handleFailure, ::handleNotificationEnable)
    }

    Handler().postDelayed(
      {
        isFirstStart.value = false
      },
      1000
    )
  }

  private fun handleNotificationEnable(isEnabled: Boolean) {
    if (isEnabled) {
      getStudyHour(UseCase.None()) {
        it.either(
          ::handleFailure
        ) { hour ->
          Timber.d("initNotification $hour")
          notificationManager.initNotification(hour)
        }
      }
    } else {
      Timber.d("cancelNextNotification")
      notificationManager.cancelNextNotification()
    }
  }
}