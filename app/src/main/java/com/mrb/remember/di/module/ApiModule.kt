package com.mrb.remember.di.module

import android.app.Application
import com.google.gson.Gson
import com.mrb.remember.BuildConfig
import com.mrb.remember.data.ConfigRepositoryImp
import com.mrb.remember.data.RememberRepositoryImp
import com.mrb.remember.data.UserRepositoryImp
import com.mrb.remember.data.api.LeitnerBoxApiService
import com.mrb.remember.data.api.UserApiService
import com.mrb.remember.data.utils.NetworkHandler
import com.mrb.remember.di.annotation.BaseUrl
import com.mrb.remember.di.annotation.UserBaseUrl
import com.mrb.remember.domain.repository.ConfigRepository
import com.mrb.remember.domain.repository.RememberRepository
import com.mrb.remember.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideConfigRepository(
        context: Application,
        gson: Gson
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
    fun provideUserRepository(
        networkHandler: NetworkHandler,
        apiService: UserApiService
    ): UserRepository =
        UserRepositoryImp(apiService, networkHandler)

    @Provides
    @Singleton
    fun provideApiService(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String
    ): LeitnerBoxApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LeitnerBoxApiService::class.java)

    @Provides
    @Singleton
    fun provideUserApiService(
        okHttpClient: OkHttpClient,
        @UserBaseUrl baseUrl: String
    ): UserApiService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UserApiService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        context: Application
    ): OkHttpClient {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            cache(cache)
            if (BuildConfig.DEBUG) addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @UserBaseUrl
    fun provideUserBaseUrl(): String = BuildConfig.USER_BASE_URL
}