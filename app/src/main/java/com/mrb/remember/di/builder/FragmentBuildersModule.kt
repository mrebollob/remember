package com.mrb.remember.di.builder

import com.mrb.remember.presentation.main.home.HomeFragment
import com.mrb.remember.presentation.main.journal.JournalFragment
import com.mrb.remember.presentation.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeHomeFragment(): HomeFragment

  @ContributesAndroidInjector
  abstract fun contributeJournalFragment(): JournalFragment

  @ContributesAndroidInjector
  abstract fun contributeProfileFragment(): ProfileFragment
}
