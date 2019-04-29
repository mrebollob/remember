package com.mrb.remember.di.builder

import com.mrb.remember.presentation.about.AboutActivity
import com.mrb.remember.presentation.levels.LevelsActivity
import com.mrb.remember.presentation.main.MainActivity
import com.mrb.remember.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLevelsActivity(): LevelsActivity

    @ContributesAndroidInjector
    abstract fun contributeAboutActivity(): AboutActivity
}
