package com.mrb.remember.data.entity

import com.mrb.remember.domain.model.User

data class UserEntity(
    val results: List<ResultEntity>
) {

    fun toUser(): User {

        return if (results.isNotEmpty()) {
            val firstResult = results[0]

            User(
                firstName = firstResult.name.first,
                lastName = firstResult.name.last,
                email = firstResult.email,
                picture = firstResult.picture.medium
            )
        } else {

            User(
                firstName = "",
                lastName = "",
                email = "",
                picture = ""
            )
        }
    }

    companion object {
        fun empty() = UserEntity(
            emptyList()
        )
    }
}

data class ResultEntity(
    val email: String,
    val name: NameEntity,
    val picture: PictureEntity
)

data class PictureEntity(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class NameEntity(
    val first: String,
    val last: String,
    val title: String
)
