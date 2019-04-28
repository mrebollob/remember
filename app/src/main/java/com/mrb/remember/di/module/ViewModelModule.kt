package com.mrb.remember.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrb.remember.di.RememberViewModelFactory
import com.mrb.remember.di.annotation.ViewModelKey
import com.mrb.remember.presentation.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel::class)
  abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: RememberViewModelFactory): ViewModelProvider.Factory
}
