package com.mrb.remember.data.entity

import com.mrb.remember.domain.extension.empty
import com.mrb.remember.domain.model.Homework
import com.mrb.remember.domain.model.Level

data class LevelEntity(
  private val day: Int,
  private val levels: List<Int>
) {

  fun toHomework(): Homework {
    val hoLevels = mutableListOf<Level>()

    levels.forEach {
      hoLevels.add(Level("level_$it", "$it", -1, it))
    }

    return Homework(day, hoLevels)
  }

  companion object {
    fun empty() = LevelEntity(
      Int.empty(),
      emptyList()
    )
  }
}