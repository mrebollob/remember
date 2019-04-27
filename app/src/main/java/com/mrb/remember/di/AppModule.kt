package com.mrb.remember.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

  @Provides
  @Singleton
  fun provideGson(): Gson = Gson()
}
