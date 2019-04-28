package com.mrb.remember.presentation

import com.mrb.remember.AndroidTest
import com.mrb.remember.domain.functional.Either.Right
import com.mrb.remember.domain.interactor.GetNotificationEnable
import com.mrb.remember.domain.interactor.GetStudyHour
import com.mrb.remember.domain.model.Hour
import com.mrb.remember.notification.NotificationManager
import com.mrb.remember.presentation.splash.FirstStartHandler
import com.mrb.remember.presentation.splash.SplashViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class SplashViewModelTest : AndroidTest() {

  private lateinit var splashViewModel: SplashViewModel

  @Mock
  private lateinit var getNotificationEnabled: GetNotificationEnable
  @Mock
  private lateinit var getStudyHour: GetStudyHour
  @Mock
  private lateinit var notificationManager: NotificationManager
  @Mock
  private lateinit var firstStartHandler: FirstStartHandler

  @Before
  fun setUp() {
    splashViewModel =
      SplashViewModel(getNotificationEnabled, getStudyHour, notificationManager, firstStartHandler)
  }

  @Test
  fun `with notifications enabled should init notifications`() {
    given { runBlocking { getNotificationEnabled.run(eq(any())) } }.willReturn(Right(true))
    given { runBlocking { getStudyHour.run(eq(any())) } }.willReturn(Right(Hour.empty()))

    verify(notificationManager).initNotification(Mockito.any(Hour::class.java))

    runBlocking { splashViewModel.init() }
  }
}