package com.mrb.remember.data

import com.mrb.remember.data.api.UserApiService
import com.mrb.remember.data.entity.UserEntity
import com.mrb.remember.data.utils.NetworkHandler
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.User
import com.mrb.remember.domain.repository.UserRepository
import retrofit2.Call

class UserRepositoryImp(
    private val apiService: UserApiService,
    private val networkHandler: NetworkHandler
) : UserRepository {

    override fun user(): Either<Failure, User> {

        return when (networkHandler.isConnected) {
            true -> request(
                apiService.user("study"),
                { it.toUser() },
                UserEntity.empty()
            )
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}