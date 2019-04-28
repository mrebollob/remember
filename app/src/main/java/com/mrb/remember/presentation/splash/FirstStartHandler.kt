package com.mrb.remember.presentation.splash

import android.content.Context

class FirstStartHandler(context: Context) {

  private val sharedPreferences =
    context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

  fun isFirstStart(): Boolean {
    return sharedPreferences.getBoolean(IS_FIRST_START, true)
  }

  fun saveFirstStart() {
    sharedPreferences.edit()
      .putBoolean(IS_FIRST_START, false)
      .apply()
  }

  companion object {
    private const val PREFERENCE_NAME = "PREFERENCE_NAME"
    private const val IS_FIRST_START = "IS_FIRST_START"
  }
}