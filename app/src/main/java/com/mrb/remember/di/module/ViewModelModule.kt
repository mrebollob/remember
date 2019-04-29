package com.mrb.remember.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrb.remember.di.RememberViewModelFactory
import com.mrb.remember.di.annotation.ViewModelKey
import com.mrb.remember.presentation.levels.LevelsViewModel
import com.mrb.remember.presentation.main.home.HomeViewModel
import com.mrb.remember.presentation.main.journal.JournalViewModel
import com.mrb.remember.presentation.main.profile.ProfileViewModel
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
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JournalViewModel::class)
    abstract fun bindJournalViewModel(journalViewModel: JournalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LevelsViewModel::class)
    abstract fun bindLevelsViewModel(LevelsViewModel: LevelsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: RememberViewModelFactory): ViewModelProvider.Factory
}
