package com.mrb.remember.screen

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.mrb.remember.R.id

open class LevelsActivityScreen : Screen<LevelsActivityScreen>() {
    val levelsLoading: KView = KView { withId(id.levelsLoading) }
    val levelsContent: KView = KView { withId(id.levelsContent) }
    val doneButton: KButton = KButton { withId(id.doneButton) }
}