package com.mrb.remember

import android.app.Activity
import android.app.Application
import com.mrb.remember.R.attr
import com.mrb.remember.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig.Builder
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber
import javax.inject.Inject

class RememberApp : Application(), HasActivityInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    initInjector()
    iniCalligraphy()
    initTimber()
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  private fun initInjector() {
    AppInjector.init(this)
  }

  private fun iniCalligraphy() {
    ViewPump.init(
      ViewPump.builder()
        .addInterceptor(
          CalligraphyInterceptor(
            Builder()
              .setDefaultFontPath("fonts/Roboto-Regular.ttf")
              .setFontAttrId(attr.fontPath)
              .build()
          )
        )
        .build()
    )
  }

  override fun activityInjector() = dispatchingAndroidInjector

}