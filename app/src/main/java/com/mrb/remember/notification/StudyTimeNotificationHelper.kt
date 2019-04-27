package com.mrb.remember.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.preference.PreferenceManager
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.ONE_DAY_MILLIS
import com.mrb.remember.domain.extension.getCalendarForToday
import com.mrb.remember.domain.interactor.GetNotificationEnable
import com.mrb.remember.domain.interactor.GetStudyHour
import com.mrb.remember.domain.interactor.UseCase
import com.mrb.remember.domain.model.Hour
import com.mrb.remember.notification.StudyTimeNotificationReceiver.Companion.CURRENT_NOTIFICATION_KEY
import com.mrb.remember.notification.StudyTimeNotificationReceiver.Companion.FIRST_NOTIFICATION_ALARM_REQUEST_CODE
import dagger.android.DaggerBroadcastReceiver
import timber.log.Timber
import java.util.Calendar
import javax.inject.Inject

class StudyTimeNotificationHelper : DaggerBroadcastReceiver() {

  @Inject
  lateinit var getNotificationEnabled: GetNotificationEnable
  @Inject
  lateinit var getStudyHour: GetStudyHour


  override fun onReceive(context: Context, intent: Intent) {
    super.onReceive(context, intent)
    Timber.d("Device booted, broadcast received, setting bedtime notification")

    getNotificationEnabled(UseCase.None()) {
      it.either(
        ::handleFailure
      ) { handleNotificationEnabled(context, it) }
    }
  }

  private fun handleNotificationEnabled(context: Context, isEnable: Boolean) {
    if (isEnable) {
      getStudyHour(UseCase.None()) {
        it.either(
          ::handleFailure
        ) { handleStudyHour(context, it) }
      }
    }
  }

  private fun handleStudyHour(context: Context, studyHour: Hour) {
    val studyTime = studyHour.getCalendarForToday()

    if (studyTime.timeInMillis < System.currentTimeMillis()) {
      studyTime.timeInMillis = studyTime.timeInMillis + ONE_DAY_MILLIS
    }

    val settings = PreferenceManager.getDefaultSharedPreferences(context)
    settings.edit().putInt(CURRENT_NOTIFICATION_KEY, 1).apply()
    setStudyTimeNotification(context, studyTime)
  }

  private fun handleFailure(failure: Failure) {
    Timber.e("Notification error: $failure")
  }

  private fun setStudyTimeNotification(context: Context, studyTime: Calendar) {
    val intent1 = Intent(context, StudyTimeNotificationReceiver::class.java)

    val pendingIntent = PendingIntent.getBroadcast(
      context,
      FIRST_NOTIFICATION_ALARM_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT
    )
    val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      am.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        studyTime.timeInMillis,
        pendingIntent
      )
    } else {
      am.setExact(AlarmManager.RTC_WAKEUP, studyTime.timeInMillis, pendingIntent)
    }
  }
}
