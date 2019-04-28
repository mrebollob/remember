package com.mrb.remember.di.module

import android.app.Application
import com.google.gson.Gson
import com.mrb.remember.BuildConfig
import com.mrb.remember.data.ConfigRepositoryImp
import com.mrb.remember.data.RememberRepositoryImp
import com.mrb.remember.data.api.LeitnerBoxApiService
import com.mrb.remember.data.utils.NetworkHandler
import com.mrb.remember.di.annotation.BaseUrl
import com.mrb.remember.domain.repository.ConfigRepository
import com.mrb.remember.domain.repository.RememberRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

  @Provides
  @Singleton
  fun provideConfigRepository(
    context: Application, gson: Gson
  ): ConfigRepository = ConfigRepositoryImp(context, gson)

  @Provides
  @Singleton
  fun provideLeitnerRepository(
    networkHandler: NetworkHandler,
    apiService: LeitnerBoxApiService
  ): RememberRepository =
    RememberRepositoryImp(apiService, networkHandler)

  @Provides
  @Singleton
  fun provideApiService(
    okHttpClient: OkHttpClient, @BaseUrl baseUrl: String
  ): LeitnerBoxApiService =
    Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build().create(LeitnerBoxApiService::class.java)


  @Provides
  @Singleton
  fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient =
    OkHttpClient.Builder().apply {
      if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor)
    }.build()

  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

  @Provides
  @BaseUrl
  fun provideBaseUrl(): String = BuildConfig.BASE_URL
}