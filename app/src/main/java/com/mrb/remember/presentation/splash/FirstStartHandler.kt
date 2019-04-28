package com.mrb.remember.presentation.splash

import android.content.SharedPreferences
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class FirstStartHandler @Inject constructor(private val preferences: SharedPreferences) {

  fun isFirstStart(): Boolean {
    return preferences.getBoolean(IS_FIRST_START, true)
  }

  fun saveFirstStart() {
    preferences.edit()
      .putBoolean(IS_FIRST_START, false)
      .apply()
  }

  companion object {
    private const val IS_FIRST_START = "IS_FIRST_START"
  }
}