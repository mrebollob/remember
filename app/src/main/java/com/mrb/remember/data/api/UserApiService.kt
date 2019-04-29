package com.mrb.remember.data.api

import com.mrb.remember.data.entity.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    companion object {
        private const val LEVELS = "/api/"
    }

    @GET(LEVELS)
    fun user(@Query("seed") seed: String): Call<UserEntity>
}