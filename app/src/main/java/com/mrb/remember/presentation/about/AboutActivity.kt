package com.mrb.remember.presentation.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.mrb.remember.BuildConfig
import com.mrb.remember.R
import com.mrb.remember.domain.extension.loadCustomTabs
import com.mrb.remember.presentation.intro.IntroActivity
import com.mrb.remember.presentation.platform.BaseActivity
import com.mrb.remember.testing.OpenForTesting
import kotlinx.android.synthetic.main.activity_about.appVersionView
import kotlinx.android.synthetic.main.activity_about.licenseView
import kotlinx.android.synthetic.main.activity_about.recommendedReadingView
import kotlinx.android.synthetic.main.activity_about.sourceCodeView
import kotlinx.android.synthetic.main.activity_about.tutorialView
import kotlinx.android.synthetic.main.toolbar.toolbar

@OpenForTesting
class AboutActivity : BaseActivity() {

  override fun layoutId() = R.layout.activity_about
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_about)

    initUI()
  }

  private fun initUI() {
    initToolbar()
    appVersionView.setValue(BuildConfig.VERSION_NAME)

    sourceCodeView.setOnClickListener { openWebViewScreen(SOURCE_CODE_URL) }
    tutorialView.setOnClickListener { goToIntroScreen() }
    recommendedReadingView.setOnClickListener { openWebViewScreen(NCASE_URL) }
    licenseView.setOnClickListener { openWebViewScreen(LICENSE_URL) }
  }

  private fun initToolbar() {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    toolbar.setNavigationOnClickListener { onBackPressed() }
  }

  fun openWebViewScreen(url: String) {
    loadCustomTabs(Uri.parse(url))
  }

  fun goToIntroScreen() {
    IntroActivity.open(this)
    finish()
  }

  companion object Navigator {

    private const val SOURCE_CODE_URL = "https://github.com/mrebollob/leitner-box"
    private const val LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0"
    private const val NCASE_URL = "https://ncase.me/remember/"

    fun open(context: Context) {
      val intent = Intent(context, AboutActivity::class.java)
      context.startActivity(intent)
    }
  }
}
