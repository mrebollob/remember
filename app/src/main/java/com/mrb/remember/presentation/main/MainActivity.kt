package com.mrb.remember.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mrb.remember.R
import com.mrb.remember.domain.extension.navigate
import com.mrb.remember.presentation.main.home.HomeFragment
import com.mrb.remember.presentation.main.journal.JournalFragment
import com.mrb.remember.presentation.main.profile.ProfileFragment
import com.mrb.remember.presentation.platform.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.navigation
import kotlinx.android.synthetic.main.toolbar.toolbar
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  override fun layoutId(): Int = R.layout.activity_main
  override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    navigate(HomeFragment.newInstance())
    initNavigation()
    initToolbar()
  }

  private fun initNavigation() {
    navigation.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.navigation_home -> {
          navigate(HomeFragment.newInstance())
          true
        }
        R.id.navigation_journal -> {
          navigate(JournalFragment.newInstance())
          true
        }
        R.id.navigation_profile -> {
          navigate(ProfileFragment.newInstance())
          true
        }
        else -> false
      }
    }
  }

  private fun initToolbar() {
    setSupportActionBar(toolbar)
  }

  companion object Navigator {

    fun open(context: Context) {
      val intent = Intent(context, MainActivity::class.java)
      context.startActivity(intent)
    }
  }
}
