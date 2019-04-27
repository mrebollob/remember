package com.mrb.remember.domain.model

import com.mrb.remember.domain.extension.empty
import java.util.Date

data class LeitnerDay(
  private val number: Int,
  val created: Date
) {
  val dayNumber: Int = Math.max(0, number)

  companion object {
    fun empty() = LeitnerDay(Int.empty(), Date(0))
  }
}