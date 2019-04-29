package com.mrb.remember

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.idle
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.mrb.remember.presentation.levels.LevelsActivity
import com.mrb.remember.screen.LevelsActivityScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LevelsViewTest {

  @Rule
  @JvmField
  val rule = ActivityTestRule(LevelsActivity::class.java)

  @Test
  fun levelsViewShouldInitWithLoading() {

    onScreen<LevelsActivityScreen> {

      idle(100L)

      levelsLoading.isVisible()
      levelsContent.isGone()
      doneButton.isGone()
    }
  }
}