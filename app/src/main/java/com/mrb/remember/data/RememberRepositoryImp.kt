package com.mrb.remember.data

import com.mrb.remember.data.api.LeitnerBoxApiService
import com.mrb.remember.data.entity.LevelEntity
import com.mrb.remember.data.utils.NetworkHandler
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.functional.Either
import com.mrb.remember.domain.model.Homework
import com.mrb.remember.domain.model.Question
import com.mrb.remember.domain.repository.RememberRepository
import retrofit2.Call

class RememberRepositoryImp(
  private val wibleApiService: LeitnerBoxApiService,
  private val networkHandler: NetworkHandler
) : RememberRepository {


  override suspend fun homework(day: Int): Either<Failure, Homework> {

    return when (networkHandler.isConnected) {
      true -> request(
        wibleApiService.levels(day),
        { it.toHomework() },
        LevelEntity.empty()
      )
      false -> Either.Left(Failure.NetworkConnection)
    }
  }

  override suspend fun questions(level: Int): Either<Failure, List<Question>> {
    TODO("not implemented")
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